package back.vybz.feed_service.comment.dto.request;

import back.vybz.feed_service.busker.domain.mongodb.TargetType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestScrollCommentDto {
    private String feedId;
    private TargetType targetType;
    private String lastId;
    private int size = 10;

    @Builder
    private RequestScrollCommentDto(String feedId,
                                    TargetType targetType,
                                    String lastId,
                                    int size) {
        this.feedId = feedId;
        this.targetType = targetType;
        this.lastId = lastId;
        this.size = size;
    }
}
