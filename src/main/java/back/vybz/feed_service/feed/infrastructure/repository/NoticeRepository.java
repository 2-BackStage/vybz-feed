package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NoticeRepository extends MongoRepository<Feed, String> , NoticeRepositoryCustom {

}
