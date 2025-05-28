package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import back.vybz.feed_service.busker.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddReelsDto;
import back.vybz.feed_service.busker.infrastructure.repository.ReelsRepository;
import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.common.util.S3UploaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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

}
