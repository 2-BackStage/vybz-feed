package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import back.vybz.feed_service.feed.vo.request.RequestUpdateNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateNoticeDto {
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

    @Builder
    public RequestUpdateNoticeDto(String id,
                                  String writerUuid,
                                  String title,
                                  String content,
                                  String location,
                                  List<String> hashTag,
                                  List<TaggedHuman> humanTag,
                                  List<FeedFile> fileList,
                                  String startedAt,
                                  String endedAt) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = WriterType.BUSKER;
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static RequestUpdateNoticeDto of(String id,
                                            RequestUpdateNoticeVo requestUpdateNoticeVo,
                                            String writerUuid) {
        return RequestUpdateNoticeDto.builder()
                .id(id)
                .writerUuid(writerUuid)
                .title(requestUpdateNoticeVo.getTitle())
                .content(requestUpdateNoticeVo.getContent())
                .location(requestUpdateNoticeVo.getLocation())
                .hashTag(requestUpdateNoticeVo.getHashTag())
                .humanTag(requestUpdateNoticeVo.getHumanTag())
                .fileList(requestUpdateNoticeVo.getFileList())
                .startedAt(requestUpdateNoticeVo.getStartedAt())
                .endedAt(requestUpdateNoticeVo.getEndedAt())
                .build();
    }
    public static Instant parseToInstant(String value) {
        if (value == null || value.isBlank()) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(value, formatter)
                .atZone(ZoneId.of("Asia/Seoul"))
                .toInstant();
    }

}
