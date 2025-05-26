package back.vybz.feed_service.like.infrastructure;

import org.bson.types.ObjectId;

public interface FeedLikeRepositoryCustom {
    public int incLikeCount(ObjectId targetId, int delta, String collectionName);
}
