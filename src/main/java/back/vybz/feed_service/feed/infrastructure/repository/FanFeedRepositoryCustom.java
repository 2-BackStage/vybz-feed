package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;

public interface FanFeedRepositoryCustom {
    void updateFanFeedById(String id, RequestUpdateFanFeedDto requestUpdateFanFeedDto);
}
