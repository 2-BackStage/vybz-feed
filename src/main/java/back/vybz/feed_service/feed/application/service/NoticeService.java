package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.feed.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddNoticeDto;

public interface NoticeService {
    ResponseAddNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto);
    void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto);
    void deleteNotice(String noticeId,String writerUuid);
}
