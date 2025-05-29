package back.vybz.feed_service.user.dto.response;

import back.vybz.feed_service.user.domain.mongodb.UserFeedFile;
import back.vybz.feed_service.user.domain.mongodb.Location;
import back.vybz.feed_service.user.domain.mongodb.UserFeed;
import back.vybz.feed_service.user.vo.response.ResponseAddFeedVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseAddFeedDto {

    private String id;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;
    private List<UserFeedFile> userFeedFileList;
    private Location location;
    private Integer commentCount;
    private Integer likeCount;

    @Builder
    public ResponseAddFeedDto(String id,
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

    public static ResponseAddFeedDto from(UserFeed userFeed) {
        return ResponseAddFeedDto.builder()
                .id(userFeed.getId())
                .content(userFeed.getContent())
                .humanTag(userFeed.getHumanTag())
                .hashTag(userFeed.getHashTag())
                .userFeedFileList(userFeed.getUserFeedFileList())
                .location(userFeed.getLocation())
                .commentCount(userFeed.getCommentCount())
                .likeCount(userFeed.getLikeCount())
                .build();
    }

    public ResponseAddFeedVo toVo() {
        return ResponseAddFeedVo.builder()
                .id(id)
                .content(content)
                .humanTag(humanTag)
                .hashTag(hashTag)
                .userFeedFileList(userFeedFileList)
                .location(location)
                .commentCount(commentCount)
                .likeCount(likeCount)
                .build();
    }
}
