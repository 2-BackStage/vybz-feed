package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.dto.request.RequestUpdateAboutDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateAboutVo {

    private String id;
    private String content;
    private List<FeedFile> fileList;
    private List<String> hashTag;

    @Builder
    public RequestUpdateAboutVo(String id,
                                String content,
                                List<FeedFile> fileList,
                                List<String> hashTag) {
        this.id = id;
        this.content = content;
        this.fileList = fileList;
        this.hashTag = hashTag;
    }
}