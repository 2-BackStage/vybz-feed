package back.vybz.feed_service.busker.vo.response;

import back.vybz.feed_service.busker.domain.mongodb.BuskerFeed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseScrollReelsVo {

        private String buskerUuid;
        private String content;
        private String thumbnailUrl;
        private String videoUrl;

        @Builder
        public ResponseScrollReelsVo(BuskerFeed feed) {
            this.buskerUuid = feed.getBuskerUuid();
            this.content = feed.getContent();
            this.thumbnailUrl = feed.getThumbnailUrl();
            this.videoUrl = feed.getFileList().get(0).getFileUrl(); 
        }

    public static List<ResponseScrollReelsVo> listFrom(List<BuskerFeed> feedList) {
        return feedList.stream()
                .map(ResponseScrollReelsVo::new)
                .toList();
        }
    }

    






