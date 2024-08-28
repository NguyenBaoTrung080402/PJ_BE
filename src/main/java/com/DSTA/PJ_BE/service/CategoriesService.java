package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.utils.DataResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CategoriesService {

    DataResponse addNewCategories(MultipartFile file, String str);

    DataResponse getAllCategories(Pageable pageable);

    DataResponse deleteCategories(Long id);

    DataResponse updateCategory(MultipartFile file, String str, Long id);

    DataResponse getCategoryDetail(Long id);
}
