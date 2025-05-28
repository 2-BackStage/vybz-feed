package back.vybz.feed_service.busker.infrastructure.repository;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import back.vybz.feed_service.busker.dto.request.RequestUpdateReelsDto;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReelsRepositoryCustomImpl implements ReelsRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public UpdateResult updateReels(String feedId, RequestUpdateReelsDto requestUpdateReelsDto){
        Query query = new Query(Criteria.where("_id").is(new ObjectId(feedId)));

        Map<String, Object> updateFields = new HashMap<>();
        if (requestUpdateReelsDto.getContent() != null) updateFields.put("content", requestUpdateReelsDto.getContent());
        if (requestUpdateReelsDto.getHumanTag() != null) updateFields.put("humanTag", requestUpdateReelsDto.getHumanTag());
        if (requestUpdateReelsDto.getHashTag() != null) updateFields.put("hashTag", requestUpdateReelsDto.getHashTag());

        updateFields.put("updatedAt", Instant.now());

        Update update = new Update();
        updateFields.forEach(update::set);

        return mongoTemplate.updateFirst(query, update, BuskerFeed.class);
    }



}
