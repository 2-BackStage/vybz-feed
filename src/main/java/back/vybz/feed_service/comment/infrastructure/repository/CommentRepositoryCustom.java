package back.vybz.feed_service.comment.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.TargetType;
import back.vybz.feed_service.comment.domain.mongodb.Comment;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommentRepositoryCustom {
    UpdateResult updateComment(String commentId, String writerUuid, String newComment);
    List<Comment> findCommentsWithScroll(String feedId, TargetType targetType, ObjectId lastId, int size);

}
