package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.service.ProductService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all-product")
    public DataResponse getAllProduct(@PageableDefault(page = 0, size = 8) Pageable pageable){
        log.debug("Controller Request Get All Product");
        DataResponse res = productService.getAllProducts(pageable);
        return res;
    }
    @GetMapping("/get-product-id/{id}")
    public DataResponse getProductById(@PathVariable("id") Long id){
        log.debug("Controller Get Product By Id");
        DataResponse res = productService.getProductId(id);
        return res;
    }

    @PostMapping("/create-product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse createProduct(MultipartHttpServletRequest data){
        log.debug("Controller Request Create Product");
        MultipartFile file = data.getFile("image");
        String str = data.getParameter("product");
        DataResponse res = productService.createProducts(file, str);
        return res;
    }

    @PutMapping("/update-product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse updateProduct (MultipartHttpServletRequest data,@PathVariable("id") Long id){
        log.debug("Controller Request Update Product");
        MultipartFile file = data.getFile("image");
        String str = data.getParameter("product");
        DataResponse res = productService.updateProducts(file, str, id);
        return res;
    }

    @DeleteMapping("/delete-product/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse deleteProduct (@PathVariable("id") Long id){
        log.debug("Controller Request Delete Product");
        DataResponse res = productService.deleteProducts(id);
        return res;
    }
}
