package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.feed.infrastructure.repository.NoticeRepository;
import back.vybz.feed_service.kafka.event.FeedDeleteEvent;
import back.vybz.feed_service.kafka.event.NoticeCreateEvent;
import back.vybz.feed_service.kafka.event.NoticeUpdateEvent;
import back.vybz.feed_service.kafka.producer.CommonKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final CommonKafkaProducer commonKafkaProducer;


    /**
     * 공지사항 등록
     */
    @Override
    @Transactional
    public void createNotice(RequestAddNoticeDto requestAddNoticeDto) {
        try {
            Feed feed = requestAddNoticeDto.toEntity();
            Feed saved = noticeRepository.save(feed);

            NoticeCreateEvent event = NoticeCreateEvent.builder()
                    .id(saved.getId())
                    .writerUuid(saved.getWriterUuid())
                    .writerType(saved.getWriterType())
                    .title(saved.getTitle())
                    .content(saved.getContent())
                    .location(saved.getLocation())
                    .hashTag(saved.getHashTag())
                    .humanTag(saved.getHumanTag())
                    .fileList(saved.getFileList())
                    .startedAt(saved.getStartedAt())
                    .endedAt(saved.getEndedAt())
                    .createdAt(saved.getCreatedAt())
                    .membership(saved.getMembership())
                    .build();

            commonKafkaProducer.send("notice-create", event);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.NOTICE_CREATE_FAIL);
        }
    }


    /**
     * 공지사항 수정
     */
    @Override
    @Transactional
    public void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto) {
        Feed feed = noticeRepository.findById(requestUpdateNoticeDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));

        if (!feed.getWriterUuid().equals(requestUpdateNoticeDto.getWriterUuid())) {
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_UPDATE_NOTICE);
        }

        noticeRepository.updateNoticeFieldsById(requestUpdateNoticeDto.getId(), requestUpdateNoticeDto);

        NoticeUpdateEvent event = NoticeUpdateEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .title(requestUpdateNoticeDto.getTitle())
                .content(requestUpdateNoticeDto.getContent())
                .location(requestUpdateNoticeDto.getLocation())
                .hashTag(requestUpdateNoticeDto.getHashTag())
                .humanTag(requestUpdateNoticeDto.getHumanTag())
                .fileList(requestUpdateNoticeDto.getFileList())
                .startedAt(parseToInstantOrNull(requestUpdateNoticeDto.getStartedAt()))
                .endedAt(parseToInstantOrNull(requestUpdateNoticeDto.getEndedAt()))
                .updatedAt(Instant.now())
                .build();

        commonKafkaProducer.send("notice-update", event);
    }


    /**
     * 공지사항 삭제
     */
    @Override
    @Transactional
    public void deleteNotice(String noticeId, String writerUuid) {
        Feed feed = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));

        if (!feed.getWriterUuid().equals(writerUuid)) {
            throw new BaseException(BaseResponseStatus.NO_AUTHORIZATION_TO_DELETE_NOTICE);
        }

        FeedDeleteEvent event = FeedDeleteEvent.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .feedType(feed.getFeedType())
                .build();

        commonKafkaProducer.send("feed-delete", event);

        noticeRepository.delete(feed);
    }

    private Instant parseToInstantOrNull(String value) {
        if (value == null || value.isBlank()) return null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(value, formatter)
                    .atZone(ZoneId.of("Asia/Seoul"))
                    .toInstant();
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INVALID_REQUEST);
        }
    }
}
