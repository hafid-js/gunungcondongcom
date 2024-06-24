package com.hafidtech.gunungcondongcom.controller;

import com.hafidtech.gunungcondongcom.model.Comment;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.payload.CommentRequest;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.security.CurrentUser;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import com.hafidtech.gunungcondongcom.services.CommentService;
import com.hafidtech.gunungcondongcom.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<PagedResponse<Comment>> getAllComments(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        PagedResponse<Comment> allComments = commentService.getAllComments(postId, page, size);
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Comment> addComment(
            @Valid @RequestBody CommentRequest commentRequest,
            @PathVariable(name = "postId") Long postId,
            @CurrentUser UserPrincipal currentUser) {

        Comment newComment = commentService.addComment(commentRequest, postId, currentUser);

        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "id") Long id) {
        Comment comment = commentService.getComment(postId, id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComment (
            @PathVariable(name = "postId") Long postId,
            @PathVariable(name = "id") Long id,
            @Valid
            @RequestBody CommentRequest commentRequest,
            @CurrentUser UserPrincipal currentUser) {

        Comment updateComment = commentService.updateComment(postId, id, commentRequest, currentUser);

        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteComment(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "id") Long id,
            @CurrentUser UserPrincipal currentUser) {

        ApiResponse response = commentService.deleteComment(postId, id, currentUser);

        HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(response, status);
    }

}
