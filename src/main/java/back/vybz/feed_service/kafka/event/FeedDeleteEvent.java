package back.vybz.feed_service.kafka.event;

import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedDeleteEvent {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private FeedType feedType;

    @Builder
    public FeedDeleteEvent(String id,
                           String writerUuid,
                           WriterType writerType,
                           FeedType feedType) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.feedType = feedType;
    }
}
