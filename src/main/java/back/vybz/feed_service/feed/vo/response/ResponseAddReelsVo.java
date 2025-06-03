package back.vybz.feed_service.feed.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseAddReelsVo {
    private String id;
    private String videoUrl;
    private String thumbnailUrl;

    @Builder
    public ResponseAddReelsVo(String id,
                               String videoUrl,
                               String thumbnailUrl) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }
}