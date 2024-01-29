package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.entity.Brand;
import com.DSTA.PJ_BE.utils.DataResponse;

public interface BrandsService {
    DataResponse createBrand(Brand brands);

    DataResponse getAllBrands();

    DataResponse updateBrand(Long id, Brand brand);

    DataResponse deleteBrand(Long id);
}
