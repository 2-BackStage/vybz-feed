package back.vybz.feed_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReelsSearchCreateEvent {

    private String id;
    private String writerUuid;
    private String content;
    private List<String> hashTag;
    private Long createdAt;
    private String thumbnailUrl;

    @Builder
    public ReelsSearchCreateEvent(String id,
                                  String writerUuid,
                                  String content,
                                  List<String> hashTag,
                                  Long createdAt,
                                  String thumbnailUrl) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.content = content;
        this.hashTag = hashTag;
        this.createdAt = createdAt;
        this.thumbnailUrl = thumbnailUrl;
    }
}
