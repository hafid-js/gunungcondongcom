package com.hafidtech.gunungcondongcom.controller;

import com.hafidtech.gunungcondongcom.model.Category;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public PagedResponse<Category> getAllCategories(

    )
}
