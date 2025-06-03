package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import back.vybz.feed_service.feed.vo.request.RequestUpdateNoticeVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
                                            RequestUpdateNoticeVo vo,
                                            String writerUuid) {
        return RequestUpdateNoticeDto.builder()
                .id(id)
                .writerUuid(writerUuid)
                .title(vo.getTitle())
                .content(vo.getContent())
                .location(vo.getLocation())
                .hashTag(vo.getHashTag())
                .humanTag(vo.getHumanTag())
                .fileList(vo.getFileList())
                .startedAt(vo.getStartedAt())
                .endedAt(vo.getEndedAt())
                .build();
    }
}
