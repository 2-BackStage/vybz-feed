package back.vybz.feed_service.busker.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateReelsVo {
    private String id;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;

    public RequestUpdateReelsVo(String id,
                                String content,
                               List<String> humanTag,
                                List<String> hashTag) {
        this.id = id;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
    }
}
