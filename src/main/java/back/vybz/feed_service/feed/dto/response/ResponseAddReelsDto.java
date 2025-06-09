package back.vybz.feed_service.feed.dto.response;

import back.vybz.feed_service.feed.domain.mongodb.Feed;
import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import back.vybz.feed_service.feed.vo.response.ResponseAddReelsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddReelsDto {
    private String id;
    private String writerUuid;
    private WriterType writerType;
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;

   @Builder
    public ResponseAddReelsDto(String id,
                               String writerUuid,
                               WriterType writerType,
                               String content,
                               String location,
                               List<String> hashTag,
                               List<TaggedHuman> humanTag,
                               List<FeedFile> fileList) {
        this.id = id;
        this.writerUuid = writerUuid;
        this.writerType = writerType;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
    }

    public static ResponseAddReelsDto from(Feed feed) {
        return ResponseAddReelsDto.builder()
                .id(feed.getId())
                .writerUuid(feed.getWriterUuid())
                .writerType(feed.getWriterType())
                .content(feed.getContent())
                .location(feed.getLocation())
                .hashTag(feed.getHashTag())
                .humanTag(feed.getHumanTag())
                .fileList(feed.getFileList())
                .build();
    }

    public ResponseAddReelsVo toVo() {
        return ResponseAddReelsVo.builder()
                .id(id)
                .content(content)
                .location(location)
                .hashTag(hashTag)
                .humanTag(humanTag)
                .fileList(fileList)
                .build();
    }
}
