package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateNoticeVo {

    private String title;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private String startedAt;
    private String endedAt;
    private String writerUuid;

    @Builder
    public RequestUpdateNoticeVo(String title,
                                 String content,
                                 String location,
                                 List<String> hashTag,
                                 List<TaggedHuman> humanTag,
                                 List<FeedFile> fileList,
                                 String startedAt,
                                 String endedAt,
                                 String writerUuid) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.writerUuid = writerUuid;
    }
}
