package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseScrollNoticeDto;
import back.vybz.feed_service.busker.infrastructure.repository.NoticeRepository;
import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import back.vybz.feed_service.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BuskerNoticeServiceImpl implements BuskerNoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 등록
     *
     * @param requestAddNoticeDto
     * @return
     */
    @Override
    public ResponseAddNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto) {
        Notice notice = requestAddNoticeDto.toEntity();
        Notice savedNotice = noticeRepository.save(notice);
        return ResponseAddNoticeDto.from(savedNotice);
    }
    /**
     * 공지사항 무한스크롤 조회
     *
     * @param requestScrollNoticeDto
     * @return
     */
    @Override
    public ResponseScrollNoticeDto getNoticeScrollList(RequestScrollNoticeDto requestScrollNoticeDto) {
        ObjectId cursor = (requestScrollNoticeDto.getLastId() != null && !requestScrollNoticeDto.getLastId().isBlank())
                           ? new ObjectId(requestScrollNoticeDto.getLastId())
                            : null;
        List<Notice> notices = noticeRepository.findWithScroll(
                requestScrollNoticeDto.getSortType(), cursor, requestScrollNoticeDto.getSize() + 1);

        CursorPage<Notice> cursorPage = CursorPage.of(notices,
                requestScrollNoticeDto.getSize(),
                notice -> notice.getId().toHexString()
        );


        return ResponseScrollNoticeDto.from(cursorPage);

    }

    /**
     * 공지사항 수정
     *
     * @param requestUpdateNoticeDto
     * @return
     */
    @Override
    public void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto) {
        ObjectId noticeId = new ObjectId(requestUpdateNoticeDto.getId());
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));

        noticeRepository.save(requestUpdateNoticeDto.toEntity());
    }

    /**
     * 공지사항 삭제
     *
     * @param noticeId
     */
    @Override
    public void deleteNotice(ObjectId noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_NOTICE));
        noticeRepository.delete(notice);
    }

}
