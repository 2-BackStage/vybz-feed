package back.vybz.feed_service.user.vo.request;

import back.vybz.feed_service.user.domain.mongodb.Location;
import back.vybz.feed_service.user.domain.mongodb.UserFeedFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateFeedVo {


    private String userUuid;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;
    private List<UserFeedFile> userFeedFileList;
    private Location location;
    
    @Builder
    public RequestUpdateFeedVo(String userUuid,
                               String content,
                               List<String> humanTag,
                               List<String> hashTag,
                               List<UserFeedFile> userFeedFileList,
                               Location location) {
        this.userUuid = userUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.userFeedFileList = userFeedFileList;
        this.location = location;
    }
}
