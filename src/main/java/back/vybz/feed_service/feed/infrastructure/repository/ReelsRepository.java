package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReelsRepository extends MongoRepository<Feed, String>, ReelsRepositoryCustom {
    Optional<Feed> findById(String id);
}
