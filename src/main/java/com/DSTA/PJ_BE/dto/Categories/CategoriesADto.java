package com.DSTA.PJ_BE.dto.Categories;

import com.DSTA.PJ_BE.utils.Common;

import java.io.IOException;

public class CategoriesADto {
    private String name;
    private String slug;
    private String imageCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        try {
            this.imageCategory = Common.convertToBase64(imageCategory);
        } catch (IOException e) {
            this.imageCategory = imageCategory;
        }
    }
}
