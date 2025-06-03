package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import com.mongodb.client.result.UpdateResult;

import java.time.Instant;
import java.util.List;

public interface ReelsRepositoryCustom {
    void updateReelsById(String id, RequestUpdateReelsDto requestUpdateReelsDto);

}
