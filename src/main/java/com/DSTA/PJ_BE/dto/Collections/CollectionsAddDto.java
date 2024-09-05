package com.DSTA.PJ_BE.dto.Collections;

import com.DSTA.PJ_BE.utils.Common;

import java.io.IOException;

public class CollectionsAddDto {
    private String name;
    private String slug;
    private String imageCollection;
    private String description;
    private Boolean isActive;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public void setProductIds(Long productId) {
        this.productId = productId;
    }
}
