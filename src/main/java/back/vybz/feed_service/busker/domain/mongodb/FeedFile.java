package back.vybz.feed_service.busker.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedFile {

    // 파일 이름
    private String fileName;

    // 파일 url
    private String fileUrl;

    // 파일 타입
    private FeedType feedType;

    @Builder
    public FeedFile(String fileName,
                    String fileUrl,
                    FeedType feedType) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.feedType = feedType;
    }

}
