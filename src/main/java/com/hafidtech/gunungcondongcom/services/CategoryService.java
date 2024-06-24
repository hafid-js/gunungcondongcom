package com.hafidtech.gunungcondongcom.services;

import com.hafidtech.gunungcondongcom.exception.UnauthorizedException;
import com.hafidtech.gunungcondongcom.model.Category;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    PagedResponse<Category> getAllCategories(int page, int size);

    ResponseEntity<Category> getCategory(Long id);

    ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser);

    ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserPrincipal currentUser) throws UnauthorizedException;

    ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException;
}
