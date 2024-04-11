package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.WishListDto.WishListAddDto;
import com.DSTA.PJ_BE.utils.DataResponse;

public interface WishListService {
    DataResponse addToWL(WishListAddDto list);

    DataResponse getAllWishList();

    DataResponse deleteWL(Long id);

    DataResponse updateWL(Long id);
}
