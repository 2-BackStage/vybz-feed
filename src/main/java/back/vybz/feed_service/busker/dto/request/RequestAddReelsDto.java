package back.vybz.feed_service.busker.dto.request;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import back.vybz.feed_service.busker.domain.mongodb.FeedFile;
import back.vybz.feed_service.busker.domain.mongodb.FeedType;
import back.vybz.feed_service.busker.domain.mongodb.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestAddReelsDto {

    private String userUuid;
    private String content;
    private List<String> humanTag;
    private List<String> hashtag;
    private FeedType feedType;
    private List<FeedFile> fileList;
    private String thumbnailUrl;
    private Location location;
    private MultipartFile videoFile;

    @Builder
    public RequestAddReelsDto(String userUuid, String content, List<String> humanTag, List<String> hashtag,
                              FeedType feedType, List<FeedFile> fileList, String thumbnailUrl,
                              Location location, MultipartFile videoFile) {
        this.userUuid = userUuid;
        this.content = content;
        this.humanTag = humanTag;
        this.hashtag = hashtag;
        this.feedType = feedType;
        this.fileList = fileList;
        this.thumbnailUrl = thumbnailUrl;
        this.location = location;
        this.videoFile = videoFile;
    }

    public void setVideoFile(MultipartFile videoFile) {
        this.videoFile = videoFile;
    }

    public BuskerFeed toEntity() {
        return BuskerFeed.builder()
                .userUuid(userUuid)
                .content(content)
                .humanTag(humanTag)
                .hashTag(hashtag)
                .feedType(feedType)
                .fileList(fileList)
                .thumbnailUrl(thumbnailUrl)
                .location(location)
                .build();
    }
}