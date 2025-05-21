package back.vybz.feed_service.like.domain.mongodb;

import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document
public class CommentLike {

    @Id
    private ObjectId id;

    // 피드 id
    @Field(name = "feed_id")
    private ObjectId feedId;

    // 피드 타입
    @Field(name = "feed_type")
    private FeedType feedType;

    // 댓글 id
    @Field(name = "comment_id")
    private ObjectId commentId;

    //댓글작성자 uuid
    @Field(name = " writer_uuid")
    private String writerUuid;

    // 피드 작성자 uuid
    @Field(name = "user_uuid")
    private String userUuid;

    //버스커 uuid
    @Field(name = "busker_uuid")
    private String buskerUuid;

}
