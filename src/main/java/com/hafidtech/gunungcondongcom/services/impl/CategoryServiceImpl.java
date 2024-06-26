package com.hafidtech.gunungcondongcom.services.impl;

import com.hafidtech.gunungcondongcom.exception.ResourceNotFoundException;
import com.hafidtech.gunungcondongcom.exception.UnauthorizedException;
import com.hafidtech.gunungcondongcom.model.Category;
import com.hafidtech.gunungcondongcom.model.role.RoleName;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.repository.CategoryRepository;
import com.hafidtech.gunungcondongcom.security.UserPrincipal;
import com.hafidtech.gunungcondongcom.services.CategoryService;
import com.hafidtech.gunungcondongcom.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PagedResponse<Category> getAllCategories(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Category> categories = categoryRepository.findAll(pageable);

        List<Category> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() : categories.getContent();

        return new PagedResponse<>(content, categories.getNumber(), categories.getSize(), categories.getTotalElements(), categories.getTotalPages(), categories.isLast());
    }

    @Override
    public ResponseEntity<Category> getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser) {
        Category newCategory = categoryRepository.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserPrincipal currentUser) throws UnauthorizedException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        if (category.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            category.setName(newCategory.getName());
            Category updateCategory = categoryRepository.save(category);
            return new ResponseEntity<>(updateCategory, HttpStatus.OK);
        }

        throw new UnauthorizedException("You don't have permission to edit this category");
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        if (category.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "You successfully deleted category"), HttpStatus.OK);
        }
        throw new UnauthorizedException("You don't have permission to delete this category");

    }
}
