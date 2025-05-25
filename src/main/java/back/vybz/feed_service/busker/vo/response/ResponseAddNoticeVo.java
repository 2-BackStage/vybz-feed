package back.vybz.feed_service.busker.vo.response;

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
public class ResponseAddNoticeVo {

    private ObjectId id;
    private String userUuid;
    private String title;
    private String description;
    private Location location;
    private List<FeedFile> fileList;
    private Instant startedAt;
    private Instant endedAt;
    private Integer likeCount;
    private Integer commentCount;
    private Instant createdAt;

    @Builder
    public ResponseAddNoticeVo(ObjectId id,
                               String userUuid,
                               String title,
                               String description,
                               Location location,
                               List<FeedFile> fileList,
                               Instant startedAt,
                               Instant endedAt,
                               Integer likeCount,
                               Integer commentCount,
                               Instant createdAt) {
        this.id = id;
        this.userUuid = userUuid;
        this.title = title;
        this.description = description;
        this.location = location;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
    }

}
