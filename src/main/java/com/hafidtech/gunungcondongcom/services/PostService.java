package com.hafidtech.gunungcondongcom.services;

import com.hafidtech.gunungcondongcom.model.Post;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.payload.PostRequest;
import com.hafidtech.gunungcondongcom.payload.PostResponse;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import org.apache.coyote.BadRequestException;

public interface PostService {

    PagedResponse<Post> getAllPosts(int page, int size) throws BadRequestException;

    PagedResponse<Post> getPostsByCreatedBy(String username, int page, int size) throws BadRequestException;

    PagedResponse<Post> getPostsByCategory(Long id, int page, int size);

    PagedResponse<Post> getPostsByTag(Long id, int page, int size);

    Post updatePost(Long id, PostRequest newPostRequest, UserPrincipal currentUser);

    ApiResponse delete(Long id, UserPrincipal currentUser);

    PostResponse addPost(PostRequest postRequest, UserPrincipal currentUser);

    Post getPost(Long id);
}
