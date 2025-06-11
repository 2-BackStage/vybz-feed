package back.vybz.feed_service.feed.vo.request;

import back.vybz.feed_service.feed.domain.mongodb.FeedFile;
import back.vybz.feed_service.feed.domain.mongodb.FeedType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddAboutVo {

    @Schema(description = "공지 제목")
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    @Schema(description = "첨부 파일")
    @Size(max = 5, message = "파일은 최대 5개까지 첨부할 수 있습니다.")
    private List<FeedFile> fileList;

    @Schema(description = "해시태그")
    private List<String> hashTag;

    @Builder
    public RequestAddAboutVo(String content,
                             List<FeedFile> fileList,
                             List<String> hashTag) {
        this.content = content;
        this.fileList = fileList;
        this.hashTag = hashTag;
    }
}
