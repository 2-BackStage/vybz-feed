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
    private String startedAt;
    private String endedAt;
    private FeedType feedType;

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
                               String endedAt) {
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.feedType = FeedType.NOTICE;
    }

    public Feed toEntity(){
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
                .feedType(feedType)
                .build();
    }
    public static RequestAddNoticeDto from(RequestAddNoticeVo requestAddNoticeVo) {
        return RequestAddNoticeDto.builder()
                .writerUuid(requestAddNoticeVo.getWriterUuid())
                .writerType(requestAddNoticeVo.getWriterType())
                .title(requestAddNoticeVo.getTitle())
                .content(requestAddNoticeVo.getContent())
                .location(requestAddNoticeVo.getLocation())
                .hashTag(requestAddNoticeVo.getHashTag())
                .humanTag(requestAddNoticeVo.getHumanTag())
                .fileList(requestAddNoticeVo.getFileList())
                .startedAt(requestAddNoticeVo.getStartedAt())
                .endedAt(requestAddNoticeVo.getEndedAt())
                .build();
    }
    private Instant parseToInstant(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(value, formatter)
                .atZone(ZoneId.of("Asia/Seoul"))
                .toInstant();
    }


}

