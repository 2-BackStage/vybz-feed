package back.vybz.feed_service.like.infrastructure;

public interface CommentLikeRepositoryCustom {
    int incLikeCount(String commentId, int delta, String collectionName);
}
