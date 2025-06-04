package back.vybz.feed_service.feed.vo.request;

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
public class RequestAddFanFeedVo {

    private String content;
    private List<TaggedHuman> humanTag;
    private List<String> hashTag;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private String location;

    @Builder
    public RequestAddFanFeedVo(
                                String content,
                                List<TaggedHuman> humanTag,
                                List<String> hashTag,
                                FeedType feedType,
                                List<FeedFile> fileList,
                                String location) {

        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.feedType = feedType;
        this.fileList = fileList;
        this.location = location;
    }
}
