package back.vybz.feed_service.comment.presentation;

import back.vybz.feed_service.comment.application.service.CommentService;
import back.vybz.feed_service.comment.dto.request.RequestAddCommentDto;
import back.vybz.feed_service.comment.dto.request.RequestUpdateCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseAddCommentDto;
import back.vybz.feed_service.comment.vo.request.RequestAddCommentVo;
import back.vybz.feed_service.comment.vo.request.RequestUpdateCommentVo;
import back.vybz.feed_service.comment.vo.response.ResponseAddCommentVo;
import back.vybz.feed_service.common.entity.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "댓글 작성 API",
            description = "피드/공지 등에 댓글을 작성합니다.",
            tags = {"COMMENT-SERVICE"}
    )
    @PostMapping
    public BaseResponseEntity<ResponseAddCommentVo> createComment(@RequestBody RequestAddCommentVo requestAddCommentVo) {
        RequestAddCommentDto requestAddCommentDto = RequestAddCommentDto.from(requestAddCommentVo);
        ResponseAddCommentDto result = commentService.createComment(requestAddCommentDto);
        return new BaseResponseEntity<>(ResponseAddCommentVo.from(result));
    }

    @Operation(
            summary = "댓글 수정 API",
            description = "피드/공지 등에 작성한 댓글을 수정합니다.",
            tags = {"COMMENT-SERVICE"}
    )
    @PutMapping("/{commentId}")
    public BaseResponseEntity<Void> updateComment(@PathVariable String commentId,
                                                  @RequestBody RequestUpdateCommentVo requestUpdateCommentVo) {
        RequestUpdateCommentDto requestUpdateCommentDto =
                RequestUpdateCommentDto.from(commentId,requestUpdateCommentVo);

        commentService.updateComment(requestUpdateCommentDto);
        return new BaseResponseEntity<>();
    }
    @Operation(
            summary = "댓글 삭제 API",
            description = "작성한 댓글을 삭제합니다. 작성자 본인만 삭제할 수 있습니다.",
            tags = {"COMMENT-SERVICE"}
    )
    @DeleteMapping("/{commentId}")
    public BaseResponseEntity<Void> deleteComment(@PathVariable String commentId,
                                                  @RequestParam String writerUuid) {
        commentService.deleteComment(commentId, writerUuid);
        return new BaseResponseEntity<>();
    }
}



