package com.hafidtech.gunungcondongcom.controller;

import com.hafidtech.gunungcondongcom.model.Tag;
import com.hafidtech.gunungcondongcom.payload.PagedResponse;
import com.hafidtech.gunungcondongcom.services.TagService;
import com.hafidtech.gunungcondongcom.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<PagedResponse<Tag>> getAllTags(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        PagedResponse<Tag> response = tagService.getAllTags(page,size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
