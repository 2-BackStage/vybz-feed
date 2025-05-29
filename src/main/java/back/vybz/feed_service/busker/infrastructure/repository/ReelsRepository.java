package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReelsRepository extends MongoRepository<BuskerFeed, ObjectId>, ReelsRepositoryCustom {
    Optional<BuskerFeed> findById(String id);
}
