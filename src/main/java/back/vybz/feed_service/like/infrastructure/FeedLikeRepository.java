package back.vybz.feed_service.like.infrastructure;

import back.vybz.feed_service.like.domain.mongodb.FeedLike;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeedLikeRepository extends MongoRepository<FeedLike, ObjectId>, FeedLikeRepositoryCustom {
    Optional<FeedLike> findByFeedIdAndUserUuid(ObjectId feedId, String userUuid);
}
