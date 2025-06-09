package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddAboutVo {

    private String content;
    private List<FeedFile> fileList;
    private List<String> hashTag;

    @Builder
    public RequestAddAboutVo(String content,
                             List<FeedFile> fileList,
                             List<String> hashTag) {
        this.content = content;
        this.fileList = fileList;
        this.hashTag = hashTag;
    }
}
