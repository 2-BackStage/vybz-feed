package back.vybz.feed_service.comment.application.service;

import back.vybz.feed_service.comment.dto.request.RequestAddCommentDto;
import back.vybz.feed_service.comment.dto.request.RequestScrollCommentDto;
import back.vybz.feed_service.comment.dto.request.RequestUpdateCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseAddCommentDto;
import back.vybz.feed_service.comment.dto.response.ResponseScrollCommentDto;

public interface CommentService {
    ResponseAddCommentDto createComment(RequestAddCommentDto requestAddCommentDto);
    ResponseScrollCommentDto getScrollCommentList(RequestScrollCommentDto requestScrollCommentDto);
    void updateComment(RequestUpdateCommentDto requestUpdateCommentDto);
    void deleteComment(String commentId, String writerUuid);
}
