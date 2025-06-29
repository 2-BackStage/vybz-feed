package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.*;
import back.vybz.feed_service.feed.vo.request.RequestAddNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddNoticeDto {

    private String writerUuid;
    private WriterType writerType;
    private String title;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private FeedType feedType;
    private String startedAt;
    private String endedAt;
    private Boolean membership;

    @Builder
    public RequestAddNoticeDto(String writerUuid,
                               WriterType writerType,
                               String title,
                               String content,
                               String location,
                               List<String> hashTag,
                               List<TaggedHuman> humanTag,
                               List<FeedFile> fileList,
                               String startedAt,
                               String endedAt,
                               Boolean membership) {
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.title = title;
        this.content = content;
        this.location = location;
        this.feedType = FeedType.NOTICE;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.membership = membership != null ? membership : false;
    }

    public static RequestAddNoticeDto from(RequestAddNoticeVo requestAddNoticeVo, String writerUuid) {
        return RequestAddNoticeDto.builder()
                .writerUuid(writerUuid)
                .writerType(WriterType.BUSKER)
                .title(requestAddNoticeVo.getTitle())
                .content(requestAddNoticeVo.getContent())
                .location(requestAddNoticeVo.getLocation())
                .hashTag(requestAddNoticeVo.getHashTag())
                .humanTag(requestAddNoticeVo.getHumanTag())
                .fileList(requestAddNoticeVo.getFileList())
                .startedAt(requestAddNoticeVo.getStartedAt())
                .endedAt(requestAddNoticeVo.getEndedAt())
                .membership(requestAddNoticeVo.getMembership())
                .build();
    }

    public Feed toEntity() {
        return Feed.builder()
                .writerUuid(writerUuid)
                .writerType(writerType)
                .title(title)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .startedAt(parseToInstant(startedAt))
                .endedAt(parseToInstant(endedAt))
                .feedType(FeedType.NOTICE)
                .membership(membership)
                .build();
    }
    private Instant parseToInstant(String value) {
        if (value == null || value.isBlank()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(value, formatter)
                .atZone(ZoneId.of("Asia/Seoul"))
                .toInstant();
    }


}

