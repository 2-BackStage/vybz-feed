package back.vybz.feed_service.feed.dto.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import back.vybz.feed_service.feed.vo.request.RequestUpdateReelsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class RequestUpdateReelsDto {

    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;

  @Builder
    public RequestUpdateReelsDto(String id,
                                 String writerUuid,
                                 String content,
                                 String location,
                                 List<String> hashTag,
                                 List<TaggedHuman> humanTag,
                                 List<FeedFile> fileList) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = WriterType.BUSKER;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
    }

    public static RequestUpdateReelsDto of(String id,
                                             RequestUpdateReelsVo requestUpdateReelsVo,
                                             String writerUuid) {
        {
            return RequestUpdateReelsDto.builder()
                    .id(id)
                    .writerUuid(writerUuid)
                    .content(requestUpdateReelsVo.getContent())
                    .location(requestUpdateReelsVo.getLocation())
                    .hashTag(requestUpdateReelsVo.getHashTag())
                    .humanTag(requestUpdateReelsVo.getHumanTag())
                    .fileList(requestUpdateReelsVo.getFileList())
                    .build();
        }
    }

}