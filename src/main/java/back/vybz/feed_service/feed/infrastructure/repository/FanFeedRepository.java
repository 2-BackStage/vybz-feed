package back.vybz.feed_service.feed.infrastructure.repository;


import back.vybz.feed_service.feed.domain.mongodb.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FanFeedRepository extends MongoRepository<Feed, String>, FanFeedRepositoryCustom {
}
