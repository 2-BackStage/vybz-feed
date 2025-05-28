package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddReelsDto;

public interface BuskerReelsService {
    ResponseAddReelsDto createReels(RequestAddReelsDto requestAddReelsDto);

}
