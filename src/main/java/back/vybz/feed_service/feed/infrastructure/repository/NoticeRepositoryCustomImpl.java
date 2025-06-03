package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<Feed> findNoticeById(String id){
        Query query = new Query(
                Criteria.where("_id").is(id)
                        .and("feedType").is(FeedType.NOTICE)
        );

        Feed result = mongoTemplate.findOne(query, Feed.class);
        return Optional.ofNullable(result);
    }

}
