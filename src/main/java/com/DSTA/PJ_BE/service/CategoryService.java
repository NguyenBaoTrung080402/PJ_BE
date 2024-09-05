package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.entity.Category;
import com.DSTA.PJ_BE.utils.DataResponse;

public interface CategoryService {
    DataResponse createCategory(Category category);

    DataResponse getAllCategories();

    DataResponse updateCategory(Long id, Category brand);

    DataResponse deleteCategory(Long id);
}
