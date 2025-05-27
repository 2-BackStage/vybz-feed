package back.vybz.feed_service.like.application.service;

import back.vybz.feed_service.like.dto.request.RequestFeedLikeDto;
import back.vybz.feed_service.like.vo.response.ResponseFeedLikeVo;

public interface FeedLikeService {
    ResponseFeedLikeVo toggleFeedLike(RequestFeedLikeDto requestFeedLikeDto);
}
