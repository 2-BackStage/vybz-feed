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
@Transactional
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    /*
        * 댓글 좋아요 토글
     */
    @Override
    public ResponseCommentLikeVo toggleCommentLike(RequestCommentLikeDto requestCommentLikeDto){
        ObjectId commentId = new ObjectId(requestCommentLikeDto.getCommentId());
        String userUuid = requestCommentLikeDto.getUserUuid();

        Optional<CommentLike> existingLike = commentLikeRepository.findByCommentIdAndUserUuid(commentId, userUuid);

        boolean liked;
        int likeCount;
        String collection = requestCommentLikeDto.getTargetType().getCollectionName();

        if(existingLike.isPresent()){
            commentLikeRepository.deleteById(existingLike.get().getId());
            likeCount = commentLikeRepository.incLikeCount(commentId, -1, collection);
            liked = false;
        } else {
            CommentLike like = CommentLike.builder()
                    .feedId(new ObjectId(requestCommentLikeDto.getFeedId()))
                    .targetType(requestCommentLikeDto.getTargetType())
                    .commentId(commentId)
                    .writerUuid(requestCommentLikeDto.getWriterUuid())
                    .userUuid(userUuid)
                    .buskerUuid(requestCommentLikeDto.getBuskerUuid())
                    .parentCommentId(
                            requestCommentLikeDto.getParentCommentId() != null &&
                                    ObjectId.isValid(requestCommentLikeDto.getParentCommentId())
                                    ? new ObjectId(requestCommentLikeDto.getParentCommentId())
                                    : null
                    )
                    .createdAt(Instant.now())
                    .build();

            commentLikeRepository.save(like);
            likeCount = commentLikeRepository.incLikeCount(commentId, 1, collection);
            liked = true;
        }

        return ResponseCommentLikeVo.builder()
                .commentId(requestCommentLikeDto.getCommentId())
                .liked(liked)
                .likeCount(likeCount)
                .build();
    }
}