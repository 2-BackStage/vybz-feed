package back.vybz.feed_service.user.application.service;

import back.vybz.feed_service.user.dto.request.RequestAddFeedDto;
import back.vybz.feed_service.user.dto.request.RequestUpdateFeedDto;
import back.vybz.feed_service.user.dto.response.ResponseAddFeedDto;

public interface UserFeedService {
    ResponseAddFeedDto createFeed(RequestAddFeedDto requestAddFeedDto);
    void updateFeed(RequestUpdateFeedDto requestUpdateFeedDto);
    void deleteFeed(String userFeedId);
}
