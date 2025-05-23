package back.vybz.feed_service.busker.vo.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class RequestAddNoticeVo {

    private String userUuid;
    private String title;
    private String description;
    private Location location;
    private List<FeedFile> fileList;
    private Instant startedAt;
    private Instant endedAt;

}
