package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import back.vybz.feed_service.busker.dto.request.RequestUpdateReelsDto;
import com.mongodb.client.result.UpdateResult;

import java.time.Instant;
import java.util.List;

public interface ReelsRepositoryCustom {
    UpdateResult updateReels(String feedId, RequestUpdateReelsDto requestUpdateReelsDto);
    List<BuskerFeed> findWithScrollByTime(String buskerUuid, Instant lastCreatedAt, int size);

}
