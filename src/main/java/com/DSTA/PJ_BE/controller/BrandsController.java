package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.entity.Brand;
import com.DSTA.PJ_BE.service.BrandsService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
    private final Logger log = LoggerFactory.getLogger(BrandsController.class);

    @Autowired
    private BrandsService brandsService;

    @PostMapping("/create-brands")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse createBrands (@RequestBody Brand brands){
        log.debug("Controller Request Create Brands");
        DataResponse res = brandsService.createBrand(brands);
        return res;
    }
    @GetMapping("/get-all-brands")
    public DataResponse getAllBrands (){
        log.debug("Controller Request Get All Brands");
        DataResponse res = brandsService.getAllBrands();
        return res;
    }
    @PutMapping("/update-brands/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse updateBrands(@PathVariable("id") Long id, @RequestBody Brand brand){
        log.debug("Controller Request Update Brands");
        DataResponse res = brandsService.updateBrand(id, brand);
        return res;
    }

    @DeleteMapping("/delete-brands/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse deleteBrands(@PathVariable("id") Long id){
        log.debug("Controller Delete Brands");
        DataResponse res = brandsService.deleteBrand(id);
        return res;
    }
}
