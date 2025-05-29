package back.vybz.feed_service.user.infrastructure;

import back.vybz.feed_service.user.domain.mongodb.UserFeed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFeedRepository extends MongoRepository<UserFeed,String> {
}
