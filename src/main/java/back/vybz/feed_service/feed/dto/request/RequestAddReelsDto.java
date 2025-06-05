package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.*;
import back.vybz.feed_service.feed.vo.request.RequestAddReelsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddReelsDto {

    private String writerUuid;
    private WriterType writerType;
    private String title;
    private String content;
    private String location;
    private FeedType feedType;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;

    @Builder
    public RequestAddReelsDto(String writerUuid,
                              WriterType writerType,
                              String title,
                              String content,
                              String location,
                              List<String> hashTag,
                              List<TaggedHuman> humanTag,
                              List<FeedFile> fileList) {
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.title = title;
        this.content = content;
        this.feedType = FeedType.REELS;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
    }

    public static RequestAddReelsDto from(RequestAddReelsVo requestAddReelsVo, String writerUuid){
        return RequestAddReelsDto.builder()
                .writerUuid(writerUuid)
                .writerType(WriterType.BUSKER)
                .title(requestAddReelsVo.getTitle())
                .content(requestAddReelsVo.getContent())
                .location(requestAddReelsVo.getLocation())
                .hashTag(requestAddReelsVo.getHashTag())
                .humanTag(requestAddReelsVo.getHumanTag())
                .fileList(requestAddReelsVo.getFileList())
                .build();
    }
    public Feed toEntity(){
        return Feed.builder()
                .writerUuid(writerUuid)
                .writerType(writerType)
                .title(title)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .feedType(FeedType.REELS)
                .build();
    }

}