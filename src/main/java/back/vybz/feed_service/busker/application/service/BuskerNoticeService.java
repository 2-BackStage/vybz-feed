package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddNoticeDto;

public interface BuskerNoticeService {
    ResponseAddNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto);
    void updateNotice(RequestUpdateNoticeDto requestUpdateNoticeDto);

}
