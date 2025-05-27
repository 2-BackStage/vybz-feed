package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestScrollNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseScrollNoticeDto;
import org.bson.types.ObjectId;

public interface BuskerNoticeService {
    ResponseAddNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto);
    ResponseScrollNoticeDto getNoticeScrollList(RequestScrollNoticeDto requestScrollNoticeDto);
    ResponseNoticeDto getNoticeDetail(String noticeId);
    void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto);
    void deleteNotice(ObjectId noticeId);
}
