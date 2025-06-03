package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddFanFeedVo {
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private List<TaggedHuman> humanTag;
    private List<String> hashTag;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private String location;
}
