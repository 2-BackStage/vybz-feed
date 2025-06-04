package back.vybz.feed_service.feed.application.service;

import back.vybz.feed_service.feed.dto.request.RequestAddFanFeedDto;
import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;
import back.vybz.feed_service.feed.dto.response.ResponseAddFanFeedDto;

public interface FanFeedService {
    ResponseAddFanFeedDto createFanFeed(RequestAddFanFeedDto requestAddFanFeedDto);
    void updateFanFeed(RequestUpdateFanFeedDto requestUpdateFanFeedDto);
    void deleteFanFeed(String fanFeedId,String writerUuid);
}
