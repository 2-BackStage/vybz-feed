package back.vybz.feed_service.feed.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Document("feed")
public class Feed {

    @Id
    private String id;

    //작성자 uuid
    private String writerUuid;

    // 작성자 타입
    private WriterType writerType;

    //공지 제목
    @Field(name = "title")
    private String title;

    // 시작 일시
    @Field(name = "started_at")
    private Instant startedAt;

    // 종료 일시
    @Field(name = "ended_at")
    private Instant endedAt;

    // 피드 내용
    @Field(name = "content")
    private String content;

    // 사람 태그
    @Field(name = "human_tag")
    private List<TaggedHuman> humanTag;

    // 해시 태그
    @Field(name = "hash_tag")
    private List<String> hashTag;

    // 피드 타입
    @Field(name = "feed_type")
    private FeedType feedType;

    // 파일 리스트
    @Field(name = "file_list")
    private List<FeedFile> fileList;

    // 위치
    @Field(name = "location")
    private String location;

    // 구독자 전용 여부
    @Field(name = "membership")
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
