//package back.vybz.feed_service.like.infrastructure;
//
//import com.mongodb.client.result.UpdateResult;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
//@RequiredArgsConstructor
//public class CommentLikeRepositoryCustomImpl implements CommentLikeRepositoryCustom {
//
//    private final MongoTemplate mongoTemplate;
//
//    @Override
//    public int incLikeCount(String commentId, int delta, String collectionName) {
//        Query query = new Query(Criteria.where("_id").is(commentId));
//        Update update = new Update().inc("like_count", delta);
//        UpdateResult result = mongoTemplate.updateFirst(query, update, collectionName);
//        return (int) result.getModifiedCount();
//    }
//}
//
//
//
//
