package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.service.CategoriesService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/create-categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse createCategories (MultipartHttpServletRequest data){
        log.debug("Controller Request Create New Categories ");
        MultipartFile file = data.getFile("imageCategory");
        String str = data.getParameter("categories");
        DataResponse res = categoriesService.addNewCategories(file, str);
        return res;
    }

    @GetMapping("/get-all-category")
    public DataResponse getAllcategory(){
        log.debug("Controller Request Get All Categories");
        DataResponse res = categoriesService.getAllCategories();
        return res;
    }

    @DeleteMapping("/delete-category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse deleteCategory(@PathVariable("id") Long id){
        log.debug("Controller Request Delete Category");
        DataResponse res = categoriesService.deleteCategories(id);
        return res;
    }

    @PutMapping("/update-category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse updateCategories(MultipartHttpServletRequest data,@PathVariable("id") Long id){
        log.debug("Controller Request Update Category");
        MultipartFile file = data.getFile("imageCategories");
        String str = data.getParameter("categories");
        DataResponse res = categoriesService.updateCategory(file, str, id);
        return res;
    }
}
