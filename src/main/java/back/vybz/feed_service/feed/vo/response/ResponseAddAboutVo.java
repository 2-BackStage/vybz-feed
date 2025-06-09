package back.vybz.feed_service.feed.vo.response;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddAboutVo {

    private String id;
    private String content;
    private List<String> hashTag;
    private List<FeedFile> fileList;

    @Builder
    public ResponseAddAboutVo(String id,
                              String content,
                              List<String> hashTag,
                              List<FeedFile> fileList) {
        this.id = id;
        this.content = content;
        this.hashTag = hashTag;
        this.fileList = fileList;
    }
}
