package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.busker.infrastructure.repository.NoticeRepository;
import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
