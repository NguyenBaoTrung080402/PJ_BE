package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.entity.Category;
import com.DSTA.PJ_BE.service.CategoryService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoriesController {
    private final Logger log = LoggerFactory.getLogger(CategoriesController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create-category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse createCategories (@RequestBody Category category){
        log.debug("Controller Request Create category");
        DataResponse res = categoryService.createCategory(category);
        return res;
    }
    @GetMapping("/get-all-categories")
    public DataResponse getListCategory (){
        log.debug("Controller Request Get All Categories");
        DataResponse res = categoryService.getAllCategories();
        return res;
    }
    @PutMapping("/update-category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse updateCategory(@PathVariable("id") Long id, @RequestBody Category brand){
        log.debug("Controller Request Update Brands");
        DataResponse res = categoryService.updateCategory(id, brand);
        return res;
    }

    @DeleteMapping("/delete-category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse deleteCategory(@PathVariable("id") Long id){
        log.debug("Controller Delete Brands");
        DataResponse res = categoryService.deleteCategory(id);
        return res;
    }

    @GetMapping("/get-detail-category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse getDetailCategories(@PathVariable("id") Long id){
        log.debug("Controller Request Get Detail Brands");
        DataResponse res = categoryService.getDetailCategory(id);
        return res;
    }
}
