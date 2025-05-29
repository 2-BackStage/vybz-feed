package back.vybz.feed_service.busker.vo.response;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.domain.mongodb.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseScrollNoticeVo {
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
    public ResponseScrollNoticeVo(String id,
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
    public static ResponseScrollNoticeVo from(Notice notice) {
        return ResponseScrollNoticeVo.builder()
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

    public static List<ResponseScrollNoticeVo> listFrom(List<Notice> notices) {
        return notices.stream()
                .map(ResponseScrollNoticeVo::from)
                .collect(Collectors.toList());
    }
}



