package back.vybz.feed_service.busker.vo.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddReelsVo {

    private String userUuid;
    private String content;
    private List<String> humanTag;
    private List<String> hashtag;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private String thumbnailUrl;
    private Location location;
    private MultipartFile videoFile;
}
