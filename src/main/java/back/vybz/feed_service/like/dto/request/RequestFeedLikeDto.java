//package back.vybz.feed_service.like.dto.request;
//
//import back.vybz.feed_service.feed.domain.mongodb.FeedType;
//import back.vybz.feed_service.feed.domain.mongodb.WriterType;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//public class RequestFeedLikeDto {
//    private String feedId;
//    private FeedType feedType;
//    private WriterType writerType;
//    private String userUuid;
//    private String buskerUuid;
//
//    @Builder
//    private RequestFeedLikeDto(String feedId,
//                               FeedType feedType,
//                                 WriterType writerType,
//                               String userUuid,
//                               String buskerUuid) {
//        this.feedId = feedId;
//        this.feedType = feedType;
//        this.writerType = writerType;
//        this.userUuid = userUuid;
//        this.buskerUuid = buskerUuid;
//    }
//}
//
