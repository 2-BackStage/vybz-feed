package back.vybz.feed_service.busker.domain.mongodb;

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

}
