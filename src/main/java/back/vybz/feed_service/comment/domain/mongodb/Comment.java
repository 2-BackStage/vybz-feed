package back.vybz.feed_service.comment.domain.mongodb;

import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Document
public class Comment {

    @Id
    private ObjectId id;

    // 피드 id
    @Field(name = "feed_id")
    private ObjectId feedId;

    // 피드 타입
    @Field(name = "feed_type")
    private FeedType feedType;

    //댓글작성자 uuid
    @Field(name = " writer_uuid")
    private String writerUuid;

    // 피드 작성자 uuid
    @Field(name = "user_uuid")
    private String userUuid;

    //버스커 uuid
    @Field(name = "busker_uuid")
    private String buskerUuid;

    // 댓글 내용
    @Field(name = "comment")
    private String comment;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public Comment(ObjectId id,
                   ObjectId feedId,
                   FeedType feedType,
                   String writerUuid,
                   String userUuid,
                   String buskerUuid,
                   String comment) {
        this.id = id;
        this.feedId = feedId;
        this.feedType = feedType;
        this.writerUuid = writerUuid;
        this.userUuid = userUuid;
        this.buskerUuid = buskerUuid;
        this.comment = comment;
    }

}
