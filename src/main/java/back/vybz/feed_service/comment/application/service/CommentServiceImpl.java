package back.vybz.feed_service.comment.application.service;

import back.vybz.feed_service.comment.domain.mongodb.Comment;
import back.vybz.feed_service.comment.dto.request.RequestAddCommentDto;
import back.vybz.feed_service.comment.dto.request.RequestUpdateCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseAddCommentDto;
import back.vybz.feed_service.comment.infrastructure.repository.CommentRepository;
import back.vybz.feed_service.common.exception.BaseException;
import back.vybz.feed_service.common.exception.BaseResponseStatus;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

   /*
    * 댓글 작성
    */
    @Override
    public ResponseAddCommentDto createComment(RequestAddCommentDto requestAddCommentDto) {
        Comment comment = requestAddCommentDto.toEntity();
        Comment savedComment = commentRepository.save(comment);
        return ResponseAddCommentDto.from(savedComment);

    }

    /*
     * 댓글 수정
     */
    @Override
    public void updateComment(RequestUpdateCommentDto requestUpdateCommentDto) {
        ObjectId commentId = new ObjectId(requestUpdateCommentDto.getCommentId());
        UpdateResult result = commentRepository.updateComment(
                commentId,
                requestUpdateCommentDto.getWriterUuid(),
                requestUpdateCommentDto.getComment()
        );

        if (result.getMatchedCount() == 0) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_COMMENT_OR_NO_AUTH);
        }
    }
    /*
     * 댓글 삭제
     */
    @Override
    public void deleteComment(String commentId, String writerUuid) {
        Comment comment = commentRepository.findById(new ObjectId(commentId))
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_COMMENT_OR_NO_AUTH));

        if (!comment.getWriterUuid().equals(writerUuid)) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_COMMENT_OR_NO_AUTH);
        }

        commentRepository.delete(comment);
    }
}
