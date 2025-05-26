package back.vybz.feed_service.comment.application.service;

import back.vybz.feed_service.comment.domain.mongodb.Comment;
import back.vybz.feed_service.comment.dto.request.RequestAddCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseAddCommentDto;
import back.vybz.feed_service.comment.infrastructure.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
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
}
