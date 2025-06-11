package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.feed.dto.request.RequestAddReelsDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;

public interface ReelsService {
    void createReels(RequestAddReelsDto requestAddReelsDto);
    void updateReels(RequestUpdateReelsDto requestUpdateReelsDto);
    void deleteReels(String reelsId,String writerUuid);
}
