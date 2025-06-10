package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddFanFeedDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;
import back.vybz.feed_service.feed.infrastructure.repository.FanFeedRepository;
import back.vybz.feed_service.kafka.event.FanFeedCreateEvent;
import back.vybz.feed_service.kafka.event.FanFeedUpdateEvent;
import back.vybz.feed_service.kafka.event.FeedDeleteEvent;
import back.vybz.feed_service.kafka.producer.CommonKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FanFeedServiceImpl implements FanFeedService {

    private final FanFeedRepository fanFeedRepository;
    private final CommonKafkaProducer commonKafkaProducer;


    /**
     * 팬 피드를 생성하는 메서드
     *
     * @param requestAddFanFeedDto 팬 피드 생성 요청 DTO
     */
    @Override
    @Transactional
    public void createFanFeed(RequestAddFanFeedDto requestAddFanFeedDto){
        try {
            Feed feed = requestAddFanFeedDto.toEntity();
            Feed saved = fanFeedRepository.save(feed);


            FanFeedCreateEvent event = FanFeedCreateEvent.builder()
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

            commonKafkaProducer.send("fanfeed-create", event);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAN_FEED_CREATE_FAIL);
        }
    }


    /**
     * 팬 피드를 업데이트하는 메서드
     * @param requestUpdateFanFeedDto 팬 피드 업데이트 요청 DTO
     */
    @Override
    @Transactional
    public void updateFanFeed(RequestUpdateFanFeedDto requestUpdateFanFeedDto){
        Feed feed = fanFeedRepository.findById(requestUpdateFanFeedDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAN_FEED_NOT_FOUND));

        if (!feed.getWriterUuid().equals(requestUpdateFanFeedDto.getWriterUuid())) {
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_UPDATE_FAN_FEED);
        }

        fanFeedRepository.updateFanFeedById(requestUpdateFanFeedDto.getId(), requestUpdateFanFeedDto);


        FanFeedUpdateEvent event = FanFeedUpdateEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .content(requestUpdateFanFeedDto.getContent())
                .location(requestUpdateFanFeedDto.getLocation())
                .hashTag(requestUpdateFanFeedDto.getHashTag())
                .humanTag(requestUpdateFanFeedDto.getHumanTag())
                .fileList(requestUpdateFanFeedDto.getFileList())
                .updatedAt(Instant.now())
                .build();

        commonKafkaProducer.send("fanfeed-update", event);
    }


    /**
     * 팬 피드를 삭제하는 메서드
     * @param id 팬 피드 ID
     */
    @Override
    @Transactional
    public void deleteFanFeed(String fanFeedId, String writerUuid){
        Feed feed = fanFeedRepository.findById(fanFeedId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.FAN_FEED_NOT_FOUND));

        if (!feed.getWriterUuid().equals(writerUuid)) {
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_DELETE_FAN_FEED);
        }


        FeedDeleteEvent event = FeedDeleteEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .feedType(feed.getFeedType())
                .build();

        commonKafkaProducer.send("feed-delete", event);

        fanFeedRepository.delete(feed);
    }


}
