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

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddNoticeVo {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private String location;

    private List<String> hashTag;

    private List<TaggedHuman> humanTag;
    @Size(max = 4, message = "파일은 최대 4개까지 첨부할 수 있습니다.")
    private List<FeedFile> fileList;

    private String startedAt;

    private String endedAt;

    @Builder
    public RequestAddNoticeVo(String title,
                              String content,
                              String location,
                              List<String> hashTag,
                              List<TaggedHuman> humanTag,
                              List<FeedFile> fileList,
                              String startedAt,
                              String endedAt) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.hashTag = hashTag;
        this.humanTag = humanTag;
        this.fileList = fileList;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
}
