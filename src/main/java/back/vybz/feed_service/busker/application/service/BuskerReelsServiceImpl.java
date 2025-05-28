package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import back.vybz.feed_service.busker.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddReelsDto;
import back.vybz.feed_service.busker.infrastructure.repository.ReelsRepository;
import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.common.util.S3UploaderUtil;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuskerReelsServiceImpl implements BuskerReelsService {

    private final ReelsRepository reelsRepository;
    private final ThumbnailService thumbnailService;
    private final S3UploaderUtil s3UploaderUtil;

    /**
     * Reels 생성
     * @param requestAddReelsDto
     * @return
     */

    @Override
    @Transactional
    public ResponseAddReelsDto createReels(RequestAddReelsDto requestAddReelsDto) {
        try {
            MultipartFile file = requestAddReelsDto.getVideoFile();

            String videoUrl = s3UploaderUtil.upload(file, "reels");

            File tempFile = File.createTempFile("temp_", file.getOriginalFilename());

            file.transferTo(tempFile);

            File thumbnailFile = thumbnailService.generateThumbnail(tempFile);

            String thumbnailUrl = s3UploaderUtil.upload(
                    new FileInputStream(thumbnailFile),
                    "reels/thumbnails",
                    thumbnailFile.getName(),
                    "image/jpeg",
                    thumbnailFile.length()
            );

            BuskerFeed feed = BuskerFeed.builder()
                    .userUuid(requestAddReelsDto.getUserUuid())
                    .content(requestAddReelsDto.getContent())
                    .humanTag(requestAddReelsDto.getHumanTag())
                    .hashTag(requestAddReelsDto.getHashtag())
                    .feedType(FeedType.REELS)
                    .fileList(List.of(
                            new FeedFile(file.getOriginalFilename(), videoUrl, FeedType.REELS)
                    ))
                    .thumbnailUrl(thumbnailUrl)
                    .location(requestAddReelsDto.getLocation())
                    .build();
            BuskerFeed saved = reelsRepository.save(feed);


            return new ResponseAddReelsDto(saved.getId().toHexString(), videoUrl, thumbnailUrl);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.REELS_CREATE_FAILED);
        }
    }

    /**
     * Reels 수정
     * @param requestUpdateReelsDto
     */
    @Override
    public void updateReels(RequestUpdateReelsDto requestUpdateReelsDto) {
        UpdateResult updateResult = reelsRepository.updateReels(
                requestUpdateReelsDto.getId(), requestUpdateReelsDto
        );

        if (updateResult.getModifiedCount() == 0) {
            throw new BaseException(BaseResponseStatus.REELS_UPDATE_FAILED);
        }
    }

    /**
     * Reels 삭제
     * @param reelsId
     */
    @Override
    @Transactional
    public void deleteReels(ObjectId reelsId) {
        BuskerFeed feed = reelsRepository.findById(reelsId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REELS_NOT_FOUND));


        for (FeedFile file : feed.getFileList()) {
            try {
                s3UploaderUtil.delete(file.getFileUrl());
            } catch (URISyntaxException e) {
                log.error("S3 파일 삭제 실패 - URL: {}", file.getFileUrl(), e);
            }
        }


        try {
            s3UploaderUtil.delete(feed.getThumbnailUrl());
        } catch (URISyntaxException e) {
            log.error("S3 썸네일 삭제 실패 - URL: {}", feed.getThumbnailUrl(), e);
        }

        reelsRepository.deleteById(reelsId);
    }




}
