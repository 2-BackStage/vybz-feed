package back.vybz.feed_service.busker.vo.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddReelsVo {

    private String buskerUuid;
    private String content;
    private List<String> humanTag;
    private List<String> hashtag;
    private FeedType feedType;
    private Location location;


    @Builder
    public RequestAddReelsVo(String buskerUuid,
                             String content,
                             List<String> humanTag,
                             List<String> hashtag,
                             FeedType feedType,
                             Location location
                            ) {
        this.buskerUuid = buskerUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashtag = hashtag;
        this.feedType = feedType;
        this.location = location;

    }
}
