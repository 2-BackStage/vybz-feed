package back.vybz.feed_service.busker.dto.response;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.vo.response.ResponseNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseNoticeDto {

    private ObjectId id;
    private String userUid;
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
    public ResponseNoticeDto(ObjectId id,
                             String userUid,
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
        this.userUid = userUid;
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

    public static ResponseNoticeDto from(Notice notice) {
        return ResponseNoticeDto.builder()
                .id(notice.getId())
                .userUid(notice.getUserUuid())
                .title(notice.getTitle())
                .description(notice.getDescription())
                .location(notice.getLocation())
                .fileList(notice.getFileList())
                .startedAt(notice.getStartedAt())
                .endedAt(notice.getEndedAt())
                .likeCount(notice.getLikeCount())
                .commentCount(notice.getCommentCount())
                .createdAt(notice.getCreatedAt())
                .build();
    }
    public ResponseNoticeVo toVo(){
        return ResponseNoticeVo.builder()
                .id(this.id)
                .userUid(this.userUid)
                .title(this.title)
                .description(this.description)
                .location(this.location)
                .fileList(this.fileList)
                .startedAt(this.startedAt)
                .endedAt(this.endedAt)
                .likeCount(this.likeCount)
                .commentCount(this.commentCount)
                .createdAt(this.createdAt)
                .build();
    }
}
