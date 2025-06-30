package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddReelsVo {

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private String location;

    private List<String> hashTag;

    private List<TaggedHuman> humanTag;

    @Size(max = 1, message = "파일은 최대 1개까지 첨부할 수 있습니다.")
    private List<FeedFile> fileList;

    private String writerUuid;

    // 구독자 전용 여부
    private Boolean membership;

    @Builder
    public RequestAddReelsVo(String content,
                             String location,
                             List<String> hashTag,
                             List<TaggedHuman> humanTag,
                             List<FeedFile> fileList,
                             Boolean membership,
                             String writerUuid) {
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.membership = membership != null ? membership : false;
        this.writerUuid = writerUuid;
    }

}
