package back.vybz.feed_service.busker.application.service;

import back.vybz.feed_service.busker.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.busker.dto.request.RequestScrollReelsDto;
import back.vybz.feed_service.busker.dto.request.RequestUpdateReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseAddReelsDto;
import back.vybz.feed_service.busker.dto.response.ResponseScrollReelsDto;
import org.bson.types.ObjectId;

public interface BuskerReelsService {
    ResponseAddReelsDto createReels(RequestAddReelsDto requestAddReelsDto);
    ResponseScrollReelsDto getReelsScrollList(RequestScrollReelsDto requestScrollReelsDto);
    void updateReels(RequestUpdateReelsDto requestUpdateReelsDto);
    void deleteReels(String reelsId);


}
