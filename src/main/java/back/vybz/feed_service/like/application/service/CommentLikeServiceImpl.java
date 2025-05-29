package back.vybz.feed_service.like.application.service;

import back.vybz.feed_service.comment.infrastructure.repository.CommentRepository;
import back.vybz.feed_service.like.domain.mongodb.CommentLike;
import back.vybz.feed_service.like.dto.request.RequestCommentLikeDto;
import back.vybz.feed_service.like.infrastructure.CommentLikeRepository;
import back.vybz.feed_service.like.vo.response.ResponseCommentLikeVo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    /*
        * 댓글 좋아요 토글
     */
    @Override
    @Transactional
    public ResponseCommentLikeVo toggleCommentLike(RequestCommentLikeDto requestCommentLikeDto) {
        String commentId = requestCommentLikeDto.getCommentId();
        String userUuid = requestCommentLikeDto.getUserUuid();
        String collection = requestCommentLikeDto.getTargetType().getCollectionName();

        Optional<CommentLike> existingLike = commentLikeRepository.findByCommentIdAndUserUuid(commentId, userUuid);

        boolean liked;
        int likeCount;

        if (existingLike.isPresent()) {
            commentLikeRepository.deleteById(existingLike.get().getId());
            likeCount = commentLikeRepository.incLikeCount(commentId, -1, collection);
            liked = false;
        } else {
            CommentLike like = CommentLike.builder()
                    .feedId(requestCommentLikeDto.getFeedId())
                    .targetType(requestCommentLikeDto.getTargetType())
                    .commentId(commentId)
                    .writerUuid(requestCommentLikeDto.getWriterUuid())
                    .userUuid(userUuid)
                    .buskerUuid(requestCommentLikeDto.getBuskerUuid())
                    .parentCommentId(requestCommentLikeDto.getParentCommentId()) // null이면 null로 들어감
                    .createdAt(Instant.now())
                    .build();

            commentLikeRepository.save(like);
            likeCount = commentLikeRepository.incLikeCount(commentId, 1, collection);
            liked = true;
        }

        return ResponseCommentLikeVo.builder()
                .commentId(commentId)
                .liked(liked)
                .likeCount(likeCount)
                .build();
    }
}