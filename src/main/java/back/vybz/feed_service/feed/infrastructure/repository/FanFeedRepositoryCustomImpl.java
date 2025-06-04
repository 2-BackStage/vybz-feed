package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.dto.request.RequestUpdateFanFeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@RequiredArgsConstructor
public class FanFeedRepositoryCustomImpl implements FanFeedRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public  void updateFanFeedById(String id, RequestUpdateFanFeedDto requestUpdateFanFeedDto){
        Query query = new Query(
                Criteria.where("_id").is(id)
                        .and("feedType").is(FeedType.FAN_FEED)
        );

        Update update = new Update();
        if (requestUpdateFanFeedDto.getContent() != null) {
            update.set("content", requestUpdateFanFeedDto.getContent());
        }
        if (requestUpdateFanFeedDto.getHumanTag() != null) {
            update.set("humanTag", requestUpdateFanFeedDto.getHumanTag());
        }
        if (requestUpdateFanFeedDto.getHashTag() != null) {
            update.set("hashTag", requestUpdateFanFeedDto.getHashTag());
        }
        if (requestUpdateFanFeedDto.getFileList() != null) {
            update.set("fileList", requestUpdateFanFeedDto.getFileList());
        }
        if (requestUpdateFanFeedDto.getLocation() != null) {
            update.set("location", requestUpdateFanFeedDto.getLocation());
        }
        mongoTemplate.updateFirst(query, update , Feed.class);
    }

}
