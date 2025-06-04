//package back.vybz.feed_service.like.dto.request;
//
//import back.vybz.feed_service.feed.domain.mongodb.WriterType;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//public class RequestCommentLikeDto {
//    private String feedId;
//    private WriterType writerType;
//    private String commentId;
//    private String parentCommentId;
//    private String userUuid;
//    private String writerUuid;
//    private String buskerUuid;
//
//    @Builder
//    private RequestCommentLikeDto(String feedId,
//                                   WriterType writerType,
//                                   String commentId,
//                                   String parentCommentId,
//                                   String userUuid,
//                                   String writerUuid,
//                                   String buskerUuid) {
//        this.feedId = feedId;
//        this.writerType = writerType;
//        this.commentId = commentId;
//        this.parentCommentId = parentCommentId;
//        this.userUuid = userUuid;
//        this.writerUuid = writerUuid;
//        this.buskerUuid = buskerUuid;
//    }
//}
