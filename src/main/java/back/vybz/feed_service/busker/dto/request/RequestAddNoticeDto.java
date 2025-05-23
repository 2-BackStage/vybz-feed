package back.vybz.feed_service.busker.dto.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import back.vybz.feed_service.busker.domain.mongodb.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddNoticeDto {

    private String userUuid;
    private String title;
    private String description;
    private Location location;
    private List<FeedFile> fileList;
    private Instant startedAt;
    private Instant endedAt;

    @Builder
    public RequestAddNoticeDto(String userUuid,
                               String title,
                               String description,
                               Location location,
                               List<FeedFile> fileList,
                               Instant startedAt,
                               Instant endedAt) {
        this.userUuid = userUuid;
        this.title = title;
        this.description = description;
        this.location = location;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Notice toEntity() {
        return Notice.builder()
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
