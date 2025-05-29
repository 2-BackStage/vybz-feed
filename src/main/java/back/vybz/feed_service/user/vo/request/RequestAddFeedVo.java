package back.vybz.feed_service.user.vo.request;

import back.vybz.feed_service.user.domain.mongodb.UserFeedFile;
import back.vybz.feed_service.user.domain.mongodb.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddFeedVo {

    private String UserUuid;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;
    private List<UserFeedFile> userFeedFileList;
    private Location location;

    @Builder
    private RequestAddFeedVo(String userUuid,
                             String content,
                             List<String> humanTag,
                             List<String> hashTag,
                             List<UserFeedFile> userFeedFileList,
                             Location location) {
        UserUuid = userUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.userFeedFileList = userFeedFileList;
        this.location = location;
    }
}
