package back.vybz.feed_service.feed.infrastructure.repository;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.dto.request.RequestUpdateNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.Instant;
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

    @Override
    public void updateNoticeFieldsById(String id, RequestUpdateNoticeDto requestUpdateNoticeDto){
            Query query = new Query(
                    Criteria.where("_id").is(id)
                            .and("feedType").is(FeedType.NOTICE)
            );

            Update update = new Update();
        if (requestUpdateNoticeDto.getWriterUuid() != null) {
            update.set("writerUuid", requestUpdateNoticeDto.getWriterUuid());
        }
        if (requestUpdateNoticeDto.getWriterType() != null) {
            update.set("writerType", requestUpdateNoticeDto.getWriterType());
        }
        if (requestUpdateNoticeDto.getTitle() != null) {
            update.set("title", requestUpdateNoticeDto.getTitle());
        }
        if (requestUpdateNoticeDto.getContent() != null) {
            update.set("content", requestUpdateNoticeDto.getContent());
        }
        if (requestUpdateNoticeDto.getLocation() != null) {
            update.set("location", requestUpdateNoticeDto.getLocation());
        }
        if (requestUpdateNoticeDto.getHashTag() != null) {
            update.set("hashTag", requestUpdateNoticeDto.getHashTag());
        }
        if (requestUpdateNoticeDto.getHumanTag() != null) {
            update.set("humanTag", requestUpdateNoticeDto.getHumanTag());
        }
        if (requestUpdateNoticeDto.getFileList() != null) {
            update.set("fileList", requestUpdateNoticeDto.getFileList());
        }
        if (requestUpdateNoticeDto.getStartedAt() != null) {
            update.set("startedAt", Instant.parse(requestUpdateNoticeDto.getStartedAt()));
        }
        if (requestUpdateNoticeDto.getEndedAt() != null) {
            update.set("endedAt", Instant.parse(requestUpdateNoticeDto.getEndedAt()));
        }

        mongoTemplate.updateFirst(query, update, Feed.class);
    }
    }


