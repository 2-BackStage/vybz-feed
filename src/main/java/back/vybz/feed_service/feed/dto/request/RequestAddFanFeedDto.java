package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddFanFeedDto {

    private String writerUuid;
    private WriterType writerType;
    private String content;
    private List<TaggedHuman> humanTag;
    private List<String> hashTag;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private String location;

    @Builder
    public RequestAddFanFeedDto(String writerUuid,
                                WriterType writerType,
                                String content,
                                List<TaggedHuman> humanTag,
                                List<String> hashTag,
                                List<FeedFile> fileList,
                                String location) {
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.feedType = FeedType.FAN_FEED;
        this.fileList = fileList;
        this.location = location;

    }

    public Feed toEntity(){
        return Feed.builder()
                .writerUuid(writerUuid)
                .writerType(writerType)
                .content(content)
                .humanTag(humanTag)
                .hashTag(hashTag)
                .feedType(feedType)
                .fileList(fileList)
                .location(location)
                .build();
    }
}
