package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NoticeRepository extends MongoRepository<Notice, ObjectId> , NoticeRepositoryCustom {
    Optional<Notice> findById(ObjectId id);
}
