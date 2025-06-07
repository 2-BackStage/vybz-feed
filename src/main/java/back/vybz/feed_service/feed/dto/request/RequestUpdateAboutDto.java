package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.vo.request.RequestUpdateAboutVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateAboutDto {

    private String id;
    private String writerUuid;
    private String content;
    private List<FeedFile> fileList;
    private List<String> hashTag;

    @Builder
    public RequestUpdateAboutDto(String id,
                                 String writerUuid,
                                 String content,
                                 List<FeedFile> fileList,
                                 List<String> hashTag) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.content = content;
        this.fileList = fileList;
        this.hashTag = hashTag;
    }

    public static RequestUpdateAboutDto of(String id,
                                           RequestUpdateAboutVo requestUpdateAboutVo,
                                           String writerUuid) {
        return RequestUpdateAboutDto.builder()
                .id(id)
                .writerUuid(writerUuid)
                .content(requestUpdateAboutVo.getContent())
                .fileList(requestUpdateAboutVo.getFileList())
                .hashTag(requestUpdateAboutVo.getHashTag())
                .build();
    }
}
