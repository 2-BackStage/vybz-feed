package back.vybz.feed_service.like.dto.request;

import back.vybz.feed_service.busker.domain.mongodb.TargetType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCommentLikeDto {
    private String feedId;
    private TargetType targetType;
    private String commentId;
    private String parentCommentId;
    private String userUuid;
    private String writerUuid;
    private String buskerUuid;

    @Builder
    private RequestCommentLikeDto(String feedId,
                                   TargetType targetType,
                                   String commentId,
                                   String parentCommentId,
                                   String userUuid,
                                   String writerUuid,
                                   String buskerUuid) {
        this.feedId = feedId;
        this.targetType = targetType;
        this.commentId = commentId;
        this.parentCommentId = parentCommentId;
        this.userUuid = userUuid;
        this.writerUuid = writerUuid;
        this.buskerUuid = buskerUuid;
    }
}
