package back.vybz.feed_service.busker.dto.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.domain.mongodb.Notice;
import back.vybz.feed_service.busker.vo.request.RequestAddNoticeVo;
import back.vybz.feed_service.busker.vo.request.RequestUpdateNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateNoticeDto {
    private String id;
    private String userUuid;
    private String title;
    private String description;
    private Location location;
    private List<FeedFile> fileList;
    private Instant startedAt;
    private Instant endedAt;

    @Builder
    private RequestUpdateNoticeDto(String id,
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


    public static RequestUpdateNoticeDto of(String id,
                                            String userUuid,
                                            RequestUpdateNoticeVo requestUpdateNoticeVo) {
        return RequestUpdateNoticeDto.builder()
                .id(id)
                .userUuid(userUuid)
                .title(requestUpdateNoticeVo.getTitle())
                .description(requestUpdateNoticeVo.getDescription())
                .location(requestUpdateNoticeVo.getLocation())
                .fileList(requestUpdateNoticeVo.getFileList())
                .startedAt(requestUpdateNoticeVo.getStartedAt())
                .endedAt(requestUpdateNoticeVo.getEndedAt())
                .build();
    }


    public Notice toEntity() {
        return Notice.builder()
                .id(id)
                .userUuid(userUuid)
                .title(title)
                .description(description)
                .location(location)
                .fileList(fileList)
                .startedAt(startedAt)
                .endedAt(endedAt)
                .build();
    }
}
