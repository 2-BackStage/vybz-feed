package back.vybz.feed_service.user.vo.response;

import back.vybz.feed_service.user.domain.mongodb.UserFeedFile;
import back.vybz.feed_service.user.domain.mongodb.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddFeedVo {

    private String id;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;
    private List<UserFeedFile> userFeedFileList;
    private Location location;
    private Integer commentCount;
    private Integer likeCount;

    @Builder
    public ResponseAddFeedVo(String id,
                                      String content,
                                      List<String> humanTag,
                                      List<String> hashTag,
                                      List<UserFeedFile> userFeedFileList,
                                      Location location,
                                      Integer commentCount,
                                      Integer likeCount) {
        this.id = id;
        this.content = content;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.userFeedFileList = userFeedFileList;
        this.location = location;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }
}
