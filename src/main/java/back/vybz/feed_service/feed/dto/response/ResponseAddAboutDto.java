package back.vybz.feed_service.feed.dto.response;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.vo.response.ResponseAddAboutVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddAboutDto {
    private String id;
    private String content;
    private List<String> hashTag;
    private List<FeedFile> fileList;

    @Builder
    public ResponseAddAboutDto(String id,
                               String content,
                               List<String> hashTag,
                               List<FeedFile> fileList) {
        this.id = id;
        this.content = content;
        this.hashTag = hashTag;
        this.fileList = fileList;
    }

    public static ResponseAddAboutDto from(Feed feed) {
        return ResponseAddAboutDto.builder()
                .id(feed.getId())
                .content(feed.getContent())
                .hashTag(feed.getHashTag())
                .fileList(feed.getFileList())
                .build();
    }

    public ResponseAddAboutVo toVo() {
        return ResponseAddAboutVo.builder()
                .id(id)
                .content(content)
                .hashTag(hashTag)
                .fileList(fileList)
                .build();
    }
}
