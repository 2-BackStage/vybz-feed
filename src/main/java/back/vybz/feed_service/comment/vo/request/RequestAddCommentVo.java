package back.vybz.feed_service.comment.vo.request;

import back.vybz.feed_service.busker.domain.mongodb.TargetType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddCommentVo {

    private String feedId;
    private TargetType targetType;
    private String writerUuid;
    private String userUuid;
    private String buskerUuid;
    private String comment;
    private String parentCommentId;
}
