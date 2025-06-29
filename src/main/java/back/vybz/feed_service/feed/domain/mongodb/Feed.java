package back.vybz.feed_service.feed.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@Document("feed")
public class Feed {

    @Id
    private String id;

    //작성자 uuid
    private String writerUuid;

    // 작성자 타입
    private WriterType writerType;

    //공지 제목
    private String title;

    // 시작 일시
    private Instant startedAt;

    // 종료 일시
    private Instant endedAt;

    // 피드 내용
    private String content;

    // 사람 태그
    private List<TaggedHuman> humanTag;

    // 해시 태그
    private List<String> hashTag;

    // 피드 타입
    private FeedType feedType;

    // 파일 리스트
    private List<FeedFile> fileList;

    // 위치
    private String location;

    // 구독자 전용 여부
    private Boolean membership;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    @Builder
    public Feed(String id,
                String writerUuid,
                WriterType writerType,
                String title,
                Instant startedAt,
                Instant endedAt,
                String content,
                List<TaggedHuman> humanTag,
                List<String> hashTag,
                FeedType feedType,
                List<FeedFile> fileList,
                String location,
                Boolean membership) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.title = title;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.feedType = feedType;
        this.fileList = fileList;
        this.location = location;
        this.membership = membership != null ? membership : false;
    }


}
