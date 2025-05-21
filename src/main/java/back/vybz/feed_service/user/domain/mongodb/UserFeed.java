package back.vybz.feed_service.user.domain.mongodb;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@Document("user_feed")
public class UserFeed {

    @Id
    private ObjectId id;

    @Field(name = "user_uuid")
    private String userUuid;

    @Field(name = "content")
    private String content;

    @Field(name = "humanTag")
    private String humanTag;

    @Field(name = "hasTag")
    private String hasTag;

    @Field(name = "file_list")
    private List<FeedFile> fileList;

    @Field(name = "location")
    private Location location;

    @Field(name = "comment_count")
    private Integer commentCount = 0;

    @Field(name = "like_count")
    private Integer likeCount = 0;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public UserFeed(String userUuid, String content, String humanTag, String hasTag,
                    List<FeedFile> fileList, Location location) {
        this.userUuid = userUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hasTag = hasTag;
        this.fileList = fileList;
        this.location = location;
    }
}
