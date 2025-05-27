package back.vybz.feed_service.like.application.service;

import back.vybz.feed_service.like.dto.request.RequestCommentLikeDto;
import back.vybz.feed_service.like.vo.response.ResponseCommentLikeVo;

public interface CommentLikeService {
    ResponseCommentLikeVo toggleCommentLike(RequestCommentLikeDto requestCommentLikeDto);
}
