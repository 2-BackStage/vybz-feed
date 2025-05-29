package back.vybz.feed_service.user.domain.mongodb;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String id;

    // 사용자 UUID
    @Field(name = "user_uuid")
    private String userUuid;

    // 피드 내용
    @Field(name = "content")
    private String content;

    // 사람 태그
    @Field(name = "human_tag")
    private List<String> humanTag;

    // 해시 태그
    @Field(name = "hash_tag")
    private List<String> hashTag;

    // 파일 리스트
    @Field(name = "user_feed_file_list")
    private List<UserFeedFile> userFeedFileList;

    // 위치 정보
    @Field(name = "location")
    private Location location;

    // 댓글 수
    @Field(name = "comment_count")
    private Integer commentCount = 0;

    // 좋아요 수
    @Field(name = "like_count")
    private Integer likeCount = 0;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;


@Builder

    public UserFeed(String id,
                    String userUuid,
                    String content,
                    List<String> humanTag,
                    List<String> hashTag,
                    List<UserFeedFile> userFeedFileList,
                    Location location,
                    Integer commentCount,
                    Integer likeCount,
                    Instant createdAt,
                    Instant updatedAt){
        this.id = id;
        this.userUuid = userUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.userFeedFileList = userFeedFileList;
        this.location = location;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
