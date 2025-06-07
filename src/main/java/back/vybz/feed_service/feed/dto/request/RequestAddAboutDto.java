package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import back.vybz.feed_service.feed.vo.request.RequestAddAboutVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddAboutDto {

    private String writerUuid;
    private WriterType writerType;
    private String content;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private List<String> hashTag;


    @Builder
    public RequestAddAboutDto(String writerUuid,
                              WriterType writerType,
                              String content,
                              FeedType feedType,
                              List<FeedFile> fileList,
                              List<String> hashTag) {
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.feedType = FeedType.ABOUT;
        this.fileList = fileList;
        this.hashTag = hashTag;
    }

    public static RequestAddAboutDto from(RequestAddAboutVo requestAddAboutVo, String writerUuid) {
        return RequestAddAboutDto.builder()
                .writerUuid(writerUuid)
                .writerType(WriterType.BUSKER)
                .content(requestAddAboutVo.getContent())
                .fileList(requestAddAboutVo.getFileList())
                .hashTag(requestAddAboutVo.getHashTag())
                .build();
    }

    public Feed toEntity() {
        return Feed.builder()
                .writerUuid(writerUuid)
                .writerType(writerType)
                .content(content)
                .feedType(FeedType.ABOUT)
                .fileList(fileList)
                .hashTag(hashTag)
                .build();
    }

}
