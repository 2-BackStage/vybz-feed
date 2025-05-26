package back.vybz.feed_service.like.dto.request;

import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import back.vybz.feed_service.busker.domain.mongodb.TargetType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestFeedLikeDto {
    private String feedId;
    private FeedType feedType;
    private TargetType targetType;
    private String userUuid;
    private String buskerUuid;

    @Builder
    private RequestFeedLikeDto(String feedId,
                               FeedType feedType,
                                 TargetType targetType,
                               String userUuid,
                               String buskerUuid) {
        this.feedId = feedId;
        this.feedType = feedType;
        this.targetType = targetType;
        this.userUuid = userUuid;
        this.buskerUuid = buskerUuid;
    }
}

