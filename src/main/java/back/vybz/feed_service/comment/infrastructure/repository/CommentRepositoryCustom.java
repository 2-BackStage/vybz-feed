package back.vybz.feed_service.comment.infrastructure.repository;

import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

public interface CommentRepositoryCustom {
    UpdateResult updateComment(ObjectId commentId, String writerUuid, String newComment);

}
