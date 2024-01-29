package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.utils.DataResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    DataResponse getAllProducts(Pageable pageable);

    DataResponse getProductId(Long id);

    DataResponse createProducts(MultipartFile file, String str);

    DataResponse updateProducts(MultipartFile file, String str, Long id);

    DataResponse deleteProducts(Long id);
}
