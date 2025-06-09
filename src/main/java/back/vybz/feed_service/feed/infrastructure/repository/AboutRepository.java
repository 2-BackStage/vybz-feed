package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AboutRepository extends MongoRepository<Feed, String>, AboutRepositoryCustom {
    boolean existsByWriterUuidAndFeedType(String writerUuid, FeedType feedType);
}