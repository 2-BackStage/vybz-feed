package back.vybz.feed_service.busker.domain.mongodb;

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
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Document("busker_feed")
public class BuskerFeed {

    @Id
    private ObjectId id;

    @Field(name = "user_uuid")
    private String userUuid;

    @Field(name = "content")
    private String content;

    @Field(name = "human_tag")
    private List<String> humanTag;

    @Field(name = "hash_tag")
    private List<String> hashTag;

    @Field(name = "feed_type")
    private FeedType feedType;

    @Field(name = "file_list")
    private List<FeedFile> fileList;

    @Field(name = "location")
    private Location location;

    @Field(name = "like_count")
    private Integer likeCount = 0;

    @Field(name = "comment_count")
    private Integer commentCount = 0;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public BuskerFeed(String userUuid, String content, List<String> humanTag, List<String> hashTag,
                      FeedType feedType, List<FeedFile> fileList, Location location) {
        this.userUuid = userUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.feedType = feedType;
        this.fileList = fileList;
        this.location = location;
    }

}
