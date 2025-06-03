package back.vybz.feed_service.feed.vo.response;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddNoticeVo {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String title;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private String startedAt;
    private String endedAt;
    private FeedType feedType;

    @Builder
    public ResponseAddNoticeVo(String id,
                               String writerUuid,
                               WriterType writerType,
                               String title,
                               String content,
                               String location,
                               List<String> hashTag,
                               List<TaggedHuman> humanTag,
                               List<FeedFile> fileList,
                               String startedAt,
                               String endedAt,
                               FeedType feedType) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.feedType = feedType;
    }
}
