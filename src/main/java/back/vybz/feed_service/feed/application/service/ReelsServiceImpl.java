package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.feed.infrastructure.repository.ReelsRepository;
import back.vybz.feed_service.kafka.event.FeedDeleteEvent;
import back.vybz.feed_service.kafka.event.ReelsCreateEvent;
import back.vybz.feed_service.kafka.event.ReelsUpdateEvent;
import back.vybz.feed_service.kafka.producer.CommonKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReelsServiceImpl implements ReelsService {

    private final ReelsRepository reelsRepository;
    private final CommonKafkaProducer commonKafkaProducer;


    /**
     * 릴스등록
     */
    @Override
    @Transactional
    public void createReels(RequestAddReelsDto requestAddReelsDto){
        try {
            Feed feed = requestAddReelsDto.toEntity();
            Feed saved = reelsRepository.save(feed);


            ReelsCreateEvent event = ReelsCreateEvent.builder()
                    .id(saved.getId())
                    .writerUuid(saved.getWriterUuid())
                    .writerType(saved.getWriterType())
                    .content(saved.getContent())
                    .location(saved.getLocation())
                    .hashTag(saved.getHashTag())
                    .humanTag(saved.getHumanTag())
                    .fileList(saved.getFileList())
                    .createdAt(saved.getCreatedAt())
                    .build();

            commonKafkaProducer.send("reels-create", event);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.REELS_SAVE_FAILED);
        }
    }


    /**
     * 릴스 수정
     */
    @Override
    @Transactional
    public void updateReels(RequestUpdateReelsDto requestUpdateReelsDto){
        Feed feed = reelsRepository.findById(requestUpdateReelsDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REELS_NOT_FOUND));

        if(!feed.getWriterUuid().equals(requestUpdateReelsDto.getWriterUuid())){
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_UPDATE_REELS);
        }

        reelsRepository.updateReelsById(requestUpdateReelsDto.getId(), requestUpdateReelsDto);


        ReelsUpdateEvent event = ReelsUpdateEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .content(requestUpdateReelsDto.getContent())
                .location(requestUpdateReelsDto.getLocation())
                .hashTag(requestUpdateReelsDto.getHashTag())
                .humanTag(requestUpdateReelsDto.getHumanTag())
                .fileList(requestUpdateReelsDto.getFileList())
                .updatedAt(Instant.now())
                .build();

        commonKafkaProducer.send("reels-update", event);
    }


    /**
     * 릴스 삭제
     */
    @Override
    @Transactional
    public void deleteReels(String reelsId, String writerUuid){
        Feed feed = reelsRepository.findById(reelsId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REELS_NOT_FOUND));

        if(!feed.getWriterUuid().equals(writerUuid)){
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_DELETE_REELS);
        }

        FeedDeleteEvent event = FeedDeleteEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .feedType(feed.getFeedType())
                .build();

        commonKafkaProducer.send("feed-delete", event);

        reelsRepository.delete(feed);
    }


}
