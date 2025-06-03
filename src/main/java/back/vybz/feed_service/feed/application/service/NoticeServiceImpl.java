package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.feed.infrastructure.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 등록
     */
    @Override
    @Transactional
    public ResponseAddNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto) {
        try {
            Feed feed = requestAddNoticeDto.toEntity();
            Feed saved = noticeRepository.save(feed);
            return ResponseAddNoticeDto.from(saved);
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

        noticeRepository.delete(feed);
    }
}
