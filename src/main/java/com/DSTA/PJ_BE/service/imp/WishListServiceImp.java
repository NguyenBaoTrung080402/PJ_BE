package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.WishListDto.WishListAddDto;
import com.DSTA.PJ_BE.dto.WishListDto.WishListDtoItf;
import com.DSTA.PJ_BE.dto.WishListDto.WishListViewDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.Product;
import com.DSTA.PJ_BE.entity.WishList;
import com.DSTA.PJ_BE.repository.ProductRepository;
import com.DSTA.PJ_BE.repository.WishListRepository;
import com.DSTA.PJ_BE.service.WishListService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WishListServiceImp implements WishListService {
    private final Logger log = LoggerFactory.getLogger(WishListServiceImp.class);

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public DataResponse addToWL(WishList wishListAddDto) {
        log.debug("Request Add To Wish List");
        DataResponse res = new DataResponse();
        try {
            Account account = Common.getCurrentUserLogin();
            Product product = productRepository.getProductByID(wishListAddDto.getProductId());

            if (product == null || account == null) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.NOT_FOUND);
                return res;
            }

            List<WishList> existingWishListItems = wishListRepository.findByUserIdAndProductId(account.getId(), product.getId());

            if (!existingWishListItems.isEmpty()) {

                WishList existingWishListItem = existingWishListItems.get(0);
                existingWishListItem.setQuantity(existingWishListItem.getQuantity() + wishListAddDto.getQuantity());
                existingWishListItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingWishListItem.getQuantity())));
                wishListRepository.save(existingWishListItem);
                res.setMessage(Constants.UPDATE_SUCCESS);
                res.setResult(existingWishListItem);

            } else {
                wishListAddDto.setUserId(account.getId());
                wishListAddDto.setProductId(product.getId());
                wishListAddDto.setPrice(product.getPrice().multiply(BigDecimal.valueOf(wishListAddDto.getQuantity())));
                wishListRepository.save(wishListAddDto);
                res.setMessage(Constants.ADD_SUCCESS);
            }

            res.setStatus(Constants.SUCCESS);
            res.setResult(wishListAddDto);
            return res;
        } catch (Exception ex) {
            log.error("Message Add To WishList___BUG: " + ex.getMessage());
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getAllWishList() {
        log.debug("Request Get All Wish List");
        DataResponse res = new DataResponse();
        try {
            Account account = Common.getCurrentUserLogin();
            List<WishListDtoItf> wishList = wishListRepository.getAllWL(account.getId());

            if(wishList == null || wishList.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            List<WishListViewDto> result = Common.mapList(wishList, WishListViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse deleteWL(Long id) {
        log.debug("Request Delete Wish List");
        DataResponse res = new DataResponse();
        try {
            WishList list = wishListRepository.getWLbyID(id);
            if(list == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            wishListRepository.delete(list);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.DELETE_SUCCESS);
            return res;

        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse updateWL(Long id) {

        return null;
    }

}
