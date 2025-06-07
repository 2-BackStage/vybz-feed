package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.dto.request.RequestUpdateAboutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AboutRepositoryCustomImpl implements AboutRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateAboutById(String id, RequestUpdateAboutDto requestUpdateAboutDto) {
        Query query = new Query(
                Criteria.where("_id").is(id)
                        .and("feedType").is(FeedType.ABOUT)
        );

        Update update = new Update();
        if (requestUpdateAboutDto.getContent() != null) {
            update.set("content", requestUpdateAboutDto.getContent());
        }
        if (requestUpdateAboutDto.getFileList() != null) {
            update.set("fileList", requestUpdateAboutDto.getFileList());
        }
        if (requestUpdateAboutDto.getHashTag() != null) {
            update.set("hashTag", requestUpdateAboutDto.getHashTag());
        }
        mongoTemplate.updateFirst(query, update, Feed.class);
    }
}
