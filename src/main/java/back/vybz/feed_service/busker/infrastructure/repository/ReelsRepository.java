package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReelsRepository extends MongoRepository<BuskerFeed, ObjectId> {
}
