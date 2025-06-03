package back.vybz.feed_service.feed.dto.response;

import back.vybz.feed_service.feed.domain.mongodb.*;
import back.vybz.feed_service.feed.vo.response.ResponseAddNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddNoticeDto {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String title;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private String startedAt;
    private String endedAt;
    private FeedType feedType;

    @Builder
    public ResponseAddNoticeDto(String id,
                                String writerUuid,
                                WriterType writerType,
                                String title,
                                String content,
                                String location,
                                List<String> hashTag,
                                List<TaggedHuman> humanTag,
                                List<FeedFile> fileList,
                                Instant startedAt,
                                Instant endedAt,
                                FeedType feedType) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = formatInstant(startedAt);
        this.endedAt = formatInstant(endedAt);
        this.feedType = feedType;
    }

    public static ResponseAddNoticeDto from(Feed feed) {
        return ResponseAddNoticeDto.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .title(feed.getTitle())
                .content(feed.getContent())
                .location(feed.getLocation())
                .hashTag(feed.getHashTag())
                .humanTag(feed.getHumanTag())
                .fileList(feed.getFileList())
                .startedAt(feed.getStartedAt())
                .endedAt(feed.getEndedAt())
                .feedType(feed.getFeedType())
                .build();
    }

    public ResponseAddNoticeVo toVo() {
        return ResponseAddNoticeVo.builder()
                .id(id)
                .writerUuid(writerUuid)
                .writerType(writerType)
                .title(title)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .startedAt(startedAt)
                .endedAt(endedAt)
                .feedType(feedType)
                .build();
    }

    private String formatInstant(Instant instant) {
        if (instant == null) return null;
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .withZone(ZoneId.of("Asia/Seoul"))
                .format(instant);
    }
}
