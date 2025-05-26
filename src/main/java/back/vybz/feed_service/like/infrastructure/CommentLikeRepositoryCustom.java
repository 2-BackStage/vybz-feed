package back.vybz.feed_service.like.infrastructure;

import org.bson.types.ObjectId;

public interface CommentLikeRepositoryCustom {
    int incLikeCount(ObjectId commentId, int delta, String collectionName);
}
