package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import com.mongodb.client.result.UpdateResult;

import java.time.Instant;
import java.util.List;

public interface ReelsRepositoryCustom {
    UpdateResult updateReels(String feedId, RequestUpdateReelsDto requestUpdateReelsDto);
    List<Feed> findWithScrollByTime(String buskerUuid, Instant lastCreatedAt, int size);

}
