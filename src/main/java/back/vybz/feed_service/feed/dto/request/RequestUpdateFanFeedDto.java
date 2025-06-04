package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import back.vybz.feed_service.feed.vo.request.RequestUpdateFanFeedVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateFanFeedDto {
    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private List<TaggedHuman> humanTag;
    private List<String> hashTag;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private String location;

    @Builder
    public RequestUpdateFanFeedDto(String id,
                                   String writerUuid,
                                   WriterType writerType,
                                   String content,
                                   List<TaggedHuman> humanTag,
                                   List<String> hashTag,
                                   List<FeedFile> fileList,
                                   String location) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.feedType = FeedType.FAN_FEED;
        this.fileList = fileList;
        this.location = location;
    }

    public static RequestUpdateFanFeedDto of(String id,
                                             RequestUpdateFanFeedVo requestUpdateFanFeedVo,
                                             String writerUuid){
        return RequestUpdateFanFeedDto.builder()
                .id(id)
                .writerUuid(writerUuid)
                .content(requestUpdateFanFeedVo.getContent())
                .humanTag(requestUpdateFanFeedVo.getHumanTag())
                .hashTag(requestUpdateFanFeedVo.getHashTag())
                .fileList(requestUpdateFanFeedVo.getFileList())
                .location(requestUpdateFanFeedVo.getLocation())
                .build();
    }

}
