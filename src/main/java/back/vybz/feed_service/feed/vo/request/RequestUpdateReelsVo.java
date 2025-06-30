package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateReelsVo {
    private String content;
    private String location;
    private List<String> hashTag;
    private List<TaggedHuman> humanTag;
    private List<FeedFile> fileList;
    private String writerUuid;

   @Builder
    public RequestUpdateReelsVo(String content,
                                String location,
                                List<String> hashTag,
                                List<TaggedHuman> humanTag,
                                List<FeedFile> fileList,
                                String writerUuid) {

        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.writerUuid = writerUuid;
    }
}
