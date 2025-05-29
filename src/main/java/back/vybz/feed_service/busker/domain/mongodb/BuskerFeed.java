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
import java.util.List;

@Getter
@NoArgsConstructor
@Document("busker_feed")
public class BuskerFeed {

    @Id
    private String id;

    // 버스커 UUID
    @Field(name = "busker_uuid")
    private String buskerUuid;

    // 피드 내용
    @Field(name = "content")
    private String content;

    // 사람 태그
    @Field(name = "human_tag")
    private List<String> humanTag;

    // 해시 태그
    @Field(name = "hash_tag")
    private List<String> hashTag;

    // 피드 타입
    @Field(name = "feed_type")
    private FeedType feedType;

    // 파일 리스트
    @Field(name = "file_list")
    private List<FeedFile> fileList;

    @Field(name = "thumbnail_url")
    private String thumbnailUrl;

    // 위치
    @Field(name = "location")
    private Location location;

    // 좋아요 수
    @Field(name = "like_count")
    private Integer likeCount = 0;

    // 댓글 수
    @Field(name = "comment_count")
    private Integer commentCount = 0;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public BuskerFeed( String id,
                      String buskerUuid,
                      String content,
                      List<String> humanTag,
                      List<String> hashTag,
                      FeedType feedType,
                      List<FeedFile> fileList,
                      String thumbnailUrl,
                      Location location) {
        this.id = id;
        this.buskerUuid = buskerUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.feedType = feedType;
        this.fileList = fileList;
        this.thumbnailUrl = thumbnailUrl;
        this.location = location;
    }

}
