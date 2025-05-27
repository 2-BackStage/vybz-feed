package back.vybz.feed_service.like.application.service;
import back.vybz.feed_service.like.domain.mongodb.FeedLike;
import back.vybz.feed_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.feed_service.like.infrastructure.FeedLikeRepository;
import back.vybz.feed_service.like.vo.response.ResponseFeedLikeVo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedLikeServiceImpl implements FeedLikeService {

    private final FeedLikeRepository feedLikeRepository;

    /*
     * 피드 좋아요 토글
     */
    @Override
    public ResponseFeedLikeVo toggleFeedLike(RequestFeedLikeDto requestFeedLikeDto) {
        ObjectId feedId = new ObjectId(requestFeedLikeDto.getFeedId());
        String userUuid = requestFeedLikeDto.getUserUuid();

        Optional<FeedLike> existingLike = feedLikeRepository.findByFeedIdAndUserUuid(feedId, userUuid);

        boolean liked;
        int likeCount;
        String collection = requestFeedLikeDto.getTargetType().getCollectionName();

        if (existingLike.isPresent()) {
            feedLikeRepository.deleteById(existingLike.get().getId());
            likeCount = feedLikeRepository.incLikeCount(feedId, -1, collection);
            liked = false;
        } else {
            FeedLike like = FeedLike.builder()
                    .feedId(feedId)
                    .feedType(requestFeedLikeDto.getFeedType())
                    .userUuid(userUuid)
                    .buskerUuid(requestFeedLikeDto.getBuskerUuid())
                    .createdAt(Instant.now())
                    .build();

            feedLikeRepository.save(like);
            likeCount = feedLikeRepository.incLikeCount(feedId, 1, collection);
            liked = true;
        }

        return ResponseFeedLikeVo.builder()
                .liked(liked)
                .likeCount(likeCount)
                .build();
    }
}
