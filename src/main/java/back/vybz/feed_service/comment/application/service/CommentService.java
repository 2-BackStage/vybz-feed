package back.vybz.feed_service.comment.application.service;

import back.vybz.feed_service.comment.dto.request.RequestAddCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseAddCommentDto;

public interface CommentService {
    ResponseAddCommentDto createComment(RequestAddCommentDto requestAddCommentDto);
}
