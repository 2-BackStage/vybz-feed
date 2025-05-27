package back.vybz.feed_service.like.presentation;

import back.vybz.feed_service.like.application.service.CommentLikeService;
import back.vybz.feed_service.like.application.service.FeedLikeService;
import back.vybz.feed_service.like.dto.request.RequestCommentLikeDto;
import back.vybz.feed_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.feed_service.like.vo.response.ResponseCommentLikeVo;
import back.vybz.feed_service.like.vo.response.ResponseFeedLikeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    private final FeedLikeService feedLikeService;
    private final CommentLikeService commentLikeService;

    @Operation(
            summary = "피드 좋아요 토글 API",
            description = "피드에 좋아요를 토글합니다. 좋아요가 없으면 추가하고, 있으면 제거합니다.",
            tags = {"LIKE-SERVICE"}
    )
    @PostMapping("/feed")
    public ResponseEntity<ResponseFeedLikeVo> toggleFeedLike(@RequestBody RequestFeedLikeDto dto) {
        return ResponseEntity.ok(feedLikeService.toggleFeedLike(dto));
    }

    @Operation(
            summary = "댓글 좋아요 토글 API",
            description = "댓글에 좋아요를 토글합니다. 좋아요가 없으면 추가하고, 있으면 제거합니다.",
            tags = {"LIKE-SERVICE"}
    )
    @PostMapping("/comment")
    public ResponseEntity<ResponseCommentLikeVo> toggleCommentLike(@RequestBody RequestCommentLikeDto dto) {
        return ResponseEntity.ok(commentLikeService.toggleCommentLike(dto));
    }
}
