package com.DSTA.PJ_BE.dto.Categories;

import com.DSTA.PJ_BE.utils.Common;

import java.io.IOException;

public class CategoriesGetAllDto {
    private String id;
    private String nameCategory;
    private String slugCategory;
    private String imgCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getSlugCategory() {
        return slugCategory;
    }

    public void setSlugCategory(String slugCategory) {
        this.slugCategory = slugCategory;
    }

    public String getImgCategory() {
        return imgCategory;
    }

    public void setImgCategory(String imgCategory) {
        try {
            this.imgCategory = Common.convertToBase64(imgCategory);
        } catch (IOException e) {
            this.imgCategory = imgCategory;
        }
    }
}
