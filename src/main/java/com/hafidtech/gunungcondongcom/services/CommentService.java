package com.hafidtech.gunungcondongcom.services;

import com.hafidtech.gunungcondongcom.model.Comment;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.payload.CommentRequest;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;

public interface CommentService {

    PagedResponse<Comment> getAllComments(Long postId, int page, int size);

    Comment addComment(CommentRequest commentRequest, Long postId, UserPrincipal currentUser);

    Comment getComment(Long postId, Long id);

    Comment updateComment(Long postId, Long id, CommentRequest commentRequest, UserPrincipal currentUser);

    ApiResponse deleteComment(Long postId, Long id, UserPrincipal currentUser);
}
