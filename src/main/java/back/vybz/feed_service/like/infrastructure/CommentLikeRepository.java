package back.vybz.feed_service.like.infrastructure;

import back.vybz.feed_service.like.domain.mongodb.CommentLike;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommentLikeRepository extends MongoRepository<CommentLike, ObjectId> , CommentLikeRepositoryCustom {
    Optional<CommentLike> findByCommentIdAndUserUuid(String commentId, String userUuid);
}
