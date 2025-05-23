package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseNoticeDto;
import back.vybz.feed_service.busker.infrastructure.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
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
    public ResponseNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto) {
        Notice notice = requestAddNoticeDto.toEntity();
        Notice savedNotice = noticeRepository.save(notice);
        return ResponseNoticeDto.from(savedNotice);
    }

}
