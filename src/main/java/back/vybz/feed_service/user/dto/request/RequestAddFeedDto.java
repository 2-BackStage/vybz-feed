package back.vybz.feed_service.user.dto.request;

import back.vybz.feed_service.user.domain.mongodb.UserFeedFile;
import back.vybz.feed_service.user.domain.mongodb.Location;
import back.vybz.feed_service.user.domain.mongodb.UserFeed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddFeedDto {

    private String userUuid;
    private String content;
    private List<String> humanTag;
    private List<String> hashTag;
    private List<UserFeedFile> userFeedFileList;
    private Location location;

    @Builder
    public RequestAddFeedDto(String userUuid,
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

    public UserFeed toEntity() {
        return UserFeed.builder()
                .userUuid(userUuid)
                .content(content)
                .humanTag(humanTag)
                .hashTag(hashTag)
                .userFeedFileList(userFeedFileList)
                .location(location)
                .build();
    }
}
