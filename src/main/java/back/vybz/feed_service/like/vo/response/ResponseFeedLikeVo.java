package back.vybz.feed_service.like.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseFeedLikeVo {

    private boolean liked;
    private int likeCount;

    @Builder
    private ResponseFeedLikeVo(boolean liked, int likeCount) {
        this.liked = liked;
        this.likeCount = likeCount;
    }
}
