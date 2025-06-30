package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import back.vybz.feed_service.feed.domain.mongodb.TaggedHuman;
import back.vybz.feed_service.feed.domain.mongodb.WriterType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddFanFeedVo {

    @Schema(description = "팬 피드 내용")
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    @Schema(description = "팬 피드에 태그된 사람들")
    private List<TaggedHuman> humanTag;

    @Schema(description = "팬 피드에 사용된 해시태그")
    private List<String> hashTag;

    @Schema(description = "팬 피드에 첨부된 파일들")
    @Size(max = 4, message = "파일은 최대 4개까지 첨부할 수 있습니다.")
    private List<FeedFile> fileList;

    @Schema(description = "위치")
    private String location;

    private String writerUuid;

    @Builder
    public RequestAddFanFeedVo(
                                String content,
                                List<TaggedHuman> humanTag,
                                List<String> hashTag,
                                List<FeedFile> fileList,
                                String location,
                                String writerUuid) {

        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.fileList = fileList;
        this.location = location;
        this.writerUuid = writerUuid;
    }
}
