package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddAboutDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateAboutDto;
import back.vybz.feed_service.feed.infrastructure.repository.AboutRepository;
import back.vybz.feed_service.kafka.event.AboutCreateEvent;
import back.vybz.feed_service.kafka.event.AboutUpdateEvent;
import back.vybz.feed_service.kafka.event.FeedDeleteEvent;
import back.vybz.feed_service.kafka.producer.CommonKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutRepository aboutRepository;
    private final CommonKafkaProducer commonKafkaProducer;


    /**
     * 소개글 등록
     */
    @Override
    @Transactional
    public void createAbout(RequestAddAboutDto requestAddAboutDto) {
        try {
            Feed feed = requestAddAboutDto.toEntity();
            Feed saved = aboutRepository.save(feed);

            AboutCreateEvent event = AboutCreateEvent.builder()
                    .id(saved.getId())
                    .writerUuid(saved.getWriterUuid())
                    .writerType(saved.getWriterType())
                    .content(saved.getContent())
                    .hashTag(saved.getHashTag())
                    .fileList(saved.getFileList())
                    .createdAt(saved.getCreatedAt())
                    .build();

            commonKafkaProducer.send("about-create", event);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.ABOUT_CREATE_FAIL);
        }
    }


    /**
     * 소개글 수정
     */
    @Override
    @Transactional
    public void updateAbout(RequestUpdateAboutDto requestUpdateAboutDto) {
        Feed feed = aboutRepository.findById(requestUpdateAboutDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ABOUT_NOT_FOUND));

        if (!feed.getWriterUuid().equals(requestUpdateAboutDto.getWriterUuid())) {
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_UPDATE_ABOUT);
        }

        aboutRepository.updateAboutById(requestUpdateAboutDto.getId(), requestUpdateAboutDto);

        AboutUpdateEvent event = AboutUpdateEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .content(requestUpdateAboutDto.getContent())
                .hashTag(requestUpdateAboutDto.getHashTag())
                .fileList(requestUpdateAboutDto.getFileList())
                .updatedAt(Instant.now())
                .build();

        commonKafkaProducer.send("about-update", event);
    }


    /**
     * 소개글 삭제
     */
    @Override
    @Transactional
    public void deleteAbout(String aboutId, String writerUuid) {
        Feed feed = aboutRepository.findById(aboutId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.ABOUT_NOT_FOUND));

        if (!feed.getWriterUuid().equals(writerUuid)) {
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_DELETE_ABOUT);
        }

        FeedDeleteEvent event = FeedDeleteEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .feedType(feed.getFeedType())
                .build();

        commonKafkaProducer.send("feed-delete", event);

        aboutRepository.delete(feed);
    }
}
