package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.dto.request.RequestAddNoticeDto;
import back.vybz.feed_service.busker.dto.response.ResponseNoticeDto;

public interface BuskerNoticeService {
    ResponseNoticeDto createNotice(RequestAddNoticeDto requestAddNoticeDto);
}
