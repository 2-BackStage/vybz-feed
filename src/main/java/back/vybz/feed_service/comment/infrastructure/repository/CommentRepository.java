package back.vybz.feed_service.comment.infrastructure.repository;

import back.vybz.feed_service.comment.domain.mongodb.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, ObjectId> {

}
