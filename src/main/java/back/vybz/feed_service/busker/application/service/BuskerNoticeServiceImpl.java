package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseScrollNoticeDto;
import back.vybz.feed_service.busker.infrastructure.repository.NoticeRepository;
import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuskerNoticeServiceImpl implements BuskerNoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 등록
     */
    @Override
    @Transactional
    public ResponseAddNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto) {
        Notice notice = requestAddNoticeDto.toEntity();
        Notice savedNotice = noticeRepository.save(notice);
        return ResponseAddNoticeDto.from(savedNotice);
    }

    /**
     * 공지사항 무한스크롤 조회
     */
    @Override
    public ResponseScrollNoticeDto getNoticeScrollList(RequestScrollNoticeDto requestScrollNoticeDto) {
        String cursor = (requestScrollNoticeDto.getLastId() != null && !requestScrollNoticeDto.getLastId().isBlank())
                ? requestScrollNoticeDto.getLastId()
                : null;

        List<Notice> notices = noticeRepository.findWithScroll(
                requestScrollNoticeDto.getSortType(), cursor, requestScrollNoticeDto.getSize() + 1);

        CursorPage<Notice> cursorPage = CursorPage.of(
                notices,
                requestScrollNoticeDto.getSize(),
                Notice::getId
        );

        return ResponseScrollNoticeDto.from(cursorPage);
    }

    /**
     * 공지사항 상세 조회
     */
    @Override
    public ResponseNoticeDto getNoticeDetail(String noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));
        return ResponseNoticeDto.from(notice);
    }

    /**
     * 공지사항 수정
     */
    @Override
    @Transactional
    public void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto) {
        String noticeId = requestUpdateNoticeDto.getId();
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));

        // 수정하려는 내용이 반영된 새 엔티티 저장
        noticeRepository.save(requestUpdateNoticeDto.toEntity());
    }

    /**
     * 공지사항 삭제
     */
    @Override
    @Transactional
    public void deleteNotice(String noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));
        noticeRepository.delete(notice);
    }
}
