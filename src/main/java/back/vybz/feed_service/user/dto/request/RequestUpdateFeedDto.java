package back.vybz.feed_service.user.dto.request;

import back.vybz.feed_service.user.domain.mongodb.Location;
import back.vybz.feed_service.user.domain.mongodb.UserFeed;
import back.vybz.feed_service.user.domain.mongodb.UserFeedFile;
import back.vybz.feed_service.user.vo.request.RequestUpdateFeedVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestUpdateFeedDto {

    private String id;
    private String userUuid;
    private List<String> humanTag;
    private List<String> hashTag;
    private List<UserFeedFile> userFeedFileList;
    private Location location;

    @Builder
    public RequestUpdateFeedDto(String id,
                                String userUuid,
                                List<String> humanTag,
                                List<String> hashTag,
                                List<UserFeedFile> userFeedFileList,
                                Location location) {
        this.id = id;
        this.userUuid = userUuid;
        this.humanTag = humanTag;
        this.hashTag = hashTag;
        this.userFeedFileList = userFeedFileList;
        this.location = location;
    }

    public static RequestUpdateFeedDto of(String id, RequestUpdateFeedVo requestUpdateFeedVo) {
        return RequestUpdateFeedDto.builder()
                .id(id)
                .userUuid(requestUpdateFeedVo.getUserUuid())
                .humanTag(requestUpdateFeedVo.getHumanTag())
                .hashTag(requestUpdateFeedVo.getHashTag())
                .userFeedFileList(requestUpdateFeedVo.getUserFeedFileList())
                .location(requestUpdateFeedVo.getLocation())
                .build();
    }

    public UserFeed toEntity() {
        return UserFeed.builder()
                .id(id)
                .userUuid(userUuid)
                .humanTag(humanTag)
                .hashTag(hashTag)
                .userFeedFileList(userFeedFileList)
                .location(location)
                .build();
    }

}
