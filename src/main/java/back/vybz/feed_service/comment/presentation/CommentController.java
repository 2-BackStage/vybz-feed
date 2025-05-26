package back.vybz.feed_service.comment.presentation;

import back.vybz.feed_service.comment.application.service.CommentService;
import back.vybz.feed_service.comment.dto.request.RequestAddCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseAddCommentDto;
import back.vybz.feed_service.comment.vo.request.RequestAddCommentVo;
import back.vybz.feed_service.comment.vo.response.ResponseAddCommentVo;
import back.vybz.feed_service.common.entity.BaseResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    }



