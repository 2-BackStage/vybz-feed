package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.dto.request.RequestUpdateReelsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReelsRepositoryCustomImpl implements ReelsRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateReelsById(String id, RequestUpdateReelsDto requestUpdateReelsDto) {
        Query query = new Query(
                Criteria.where("_id").is(id)
                        .and("feedType").is(FeedType.REELS)
        );


        Update update = new Update();
        if (requestUpdateReelsDto.getContent() != null) {
            update.set("content", requestUpdateReelsDto.getContent());
        }
        if (requestUpdateReelsDto.getHumanTag() != null) {
            update.set("humanTag", requestUpdateReelsDto.getHumanTag());
        }
        if (requestUpdateReelsDto.getHashTag() != null) {
            update.set("hashTag", requestUpdateReelsDto.getHashTag());
        }
        if (requestUpdateReelsDto.getFileList() != null) {
            update.set("fileList", requestUpdateReelsDto.getFileList());
        }
        if (requestUpdateReelsDto.getLocation() != null) {
            update.set("location", requestUpdateReelsDto.getLocation());
        }
        mongoTemplate.updateFirst(query, update, Feed.class);


    }







}
