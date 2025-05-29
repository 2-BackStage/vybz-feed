package back.vybz.feed_service.busker.dto.response;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.vo.response.ResponseAddNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddNoticeDto {

    private String id;
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
    public ResponseAddNoticeDto(String id,
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

    public static ResponseAddNoticeDto from(Notice notice) {
        return ResponseAddNoticeDto.builder()
                .id(notice.getId())
                .userUuid(notice.getUserUuid())
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
    public ResponseAddNoticeVo toVo(){
        return ResponseAddNoticeVo.builder()
                .id(this.id)
                .userUuid(this.userUuid)
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
