package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.*;
import back.vybz.feed_service.feed.vo.request.RequestAddFanFeedVo;
import io.swagger.v3.oas.annotations.media.Schema;
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

    public static RequestAddFanFeedDto from(RequestAddFanFeedVo requestAddFanFeedVo, String writerUuid){
        return RequestAddFanFeedDto.builder()
                .writerUuid(writerUuid)
                .writerType(WriterType.USER)
                .content(requestAddFanFeedVo.getContent())
                .humanTag(requestAddFanFeedVo.getHumanTag())
                .hashTag(requestAddFanFeedVo.getHashTag())
                .fileList(requestAddFanFeedVo.getFileList())
                .location(requestAddFanFeedVo.getLocation())
                .build();
    }

    public Feed toEntity(){
        return Feed.builder()
                .writerUuid(writerUuid)
                .writerType(writerType)
                .content(content)
                .humanTag(humanTag)
                .hashTag(hashTag)
                .feedType(FeedType.FAN_FEED)
                .fileList(fileList)
                .location(location)
                .build();
    }
}
