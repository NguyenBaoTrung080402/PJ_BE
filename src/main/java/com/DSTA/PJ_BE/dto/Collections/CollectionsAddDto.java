package com.DSTA.PJ_BE.dto.Collections;

import com.DSTA.PJ_BE.utils.Common;

import java.io.IOException;

public class CollectionsAddDto {
    private String name;
    private String slug;
    private String imageCollection;
    private String description;
    private String isActive;
    private Long categoryId;
    private Long productId;

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

    public String getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(String imageCollection) {
        try {
            this.imageCollection = Common.convertToBase64(imageCollection);
        } catch (IOException e) {
            this.imageCollection = imageCollection;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public Boolean getIsActive() {
    //     return isActive;
    // }
    
    // public void setIsActive(Boolean isActive) {
    //     this.isActive = isActive;
    // }

    public String getIsActive() {
        return isActive;
    }
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
