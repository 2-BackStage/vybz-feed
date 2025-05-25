package back.vybz.feed_service.busker.vo.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateNoticeVo {
    private String id;
    private String userUuid;
    private String title;
    private String description;
    private Location location;
    private List<FeedFile> fileList;
    private Instant startedAt;
    private Instant endedAt;

    @Builder
    private RequestUpdateNoticeVo(String id,
                                  String userUuid,
                                  String title,
                                  String description,
                                  Location location,
                                  List<FeedFile> fileList,
                                  Instant startedAt,
                                  Instant endedAt) {
        this.id = id;
        this.userUuid = userUuid;
        this.title = title;
        this.description = description;
        this.location = location;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

}
