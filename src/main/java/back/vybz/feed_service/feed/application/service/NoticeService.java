package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.feed.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;

public interface NoticeService {
    void createNotice(RequestAddNoticeDto requestAddNoticeDto);
    void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto);
    void deleteNotice(String noticeId, String writerUuid);
}
