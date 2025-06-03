package back.vybz.feed_service.feed.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseAddReelsDto {
    private String id;
    private String videoUrl;
    private String thumbnailUrl;

    @Builder
    public ResponseAddReelsDto(String id,
                               String videoUrl,
                               String thumbnailUrl) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }
}
