package back.vybz.feed_service.like.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseCommentLikeVo {
    private String commentId;
    private boolean liked;
    private int likeCount;

    @Builder
    private ResponseCommentLikeVo(String commentId, boolean liked, int likeCount) {
        this.commentId = commentId;
        this.liked = liked;
        this.likeCount = likeCount;
    }
}
