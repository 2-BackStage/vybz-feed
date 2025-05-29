package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Notice> findWithScroll(String sortType, String lastId, int size){
        Query query = new Query();

        if (lastId != null){
            query.addCriteria(Criteria.where("_id").lt(lastId));
        }

        Sort sort = switch (sortType){
            case "LIKES" -> Sort.by(Sort.Order.desc("likeCount"),Sort.Order.desc("_id"));
            case "COMMENTS" -> Sort.by(Sort.Order.desc("commentCount"),Sort.Order.desc("_id"));
            default -> Sort.by(Sort.Order.desc("_id"));
        };

        query.with(sort).limit(size + 1);

        return mongoTemplate.find(query, Notice.class);
    }
}
