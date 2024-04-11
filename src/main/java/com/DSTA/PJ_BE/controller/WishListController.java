package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.dto.WishListDto.WishListAddDto;
import com.DSTA.PJ_BE.service.WishListService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wish-list")
public class WishListController {
    private final Logger log = LoggerFactory.getLogger(WishListController.class);

    @Autowired
    private WishListService wishListService;

    @PostMapping("/add-to-wish-list")
    public DataResponse addToWishList(@RequestBody WishListAddDto wishListAddDto) {
        log.debug("Controller Request Add To Wish List");
        DataResponse res = wishListService.addToWL(wishListAddDto);
        return res;
    }

    @GetMapping("/get-wish-list")
    public DataResponse viewWishList(){
        log.debug("Controller Request Get All Wish List");
        DataResponse res = wishListService.getAllWishList();
        return res;
    }
    @DeleteMapping("/delete-wish-list/{id}")
    public DataResponse deleteWishList(@PathVariable("id") Long id){
        log.debug("Controller Request Delete Wish List");
        DataResponse res = wishListService.deleteWL(id);
        return res;
    }
    @PutMapping("/update-wish-list")
    public DataResponse updateWishList(@PathVariable("id") Long id){
        log.debug("Controller Request Update Wish List");
        DataResponse res = wishListService.updateWL(id);
        return res;
    }
}
