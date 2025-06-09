package back.vybz.feed_service.kafka.event;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class AboutCreateEvent {
    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private List<String> hashTag;
    private List<FeedFile> fileList;
    private Instant createdAt;

    @Builder
    public AboutCreateEvent(String id,
                            String writerUuid,
                            WriterType writerType,
                            String content,
                            List<String> hashTag,
                            List<FeedFile> fileList,
                            Instant createdAt) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.hashTag = hashTag;
        this.fileList = fileList;
        this.createdAt = createdAt;
    }


}
