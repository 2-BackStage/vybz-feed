package back.vybz.feed_service.busker.domain.mongodb;

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
@Document("notice")
public class Notice {

    @Id
    private ObjectId id;

    // 버스킹 uuid
    @Field(name = "user_uuid")
    private String userUuid;

    // 공지 제목
    @Field(name = "title")
    private String title;

    // 공지 내용
    @Field(name = "description")
    private String description;

    // 위치
    @Field(name = "location")
    private Location location;

    // 파일 리스트
    @Field(name = "file_list")
    private List<FeedFile> fileList;

    // 시작 일시
    @Field(name = "started_at")
    private Instant startedAt;

    // 종료 일시
    @Field(name = "ended_at")
    private Instant endedAt;

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
    public Notice(String userUuid, String title, String description, List<FeedFile> fileList,
                  Instant startedAt, Instant endedAt, Location location) {
        this.userUuid = userUuid;
        this.title = title;
        this.description = description;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.location = location;
    }

}
