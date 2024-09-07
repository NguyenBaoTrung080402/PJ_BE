package com.DSTA.PJ_BE.dto.Collections;

import com.DSTA.PJ_BE.utils.Common;

import java.io.IOException;

public class CollectionsGetAllDto {
    private String id;
    private String nameCollection;
    private String slugCollection;
    private String imgCollection;
    private String description;
    private Boolean isActive;
    private String categoryName;
    private String ProductName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCollection() {
        return nameCollection;
    }

    public void setNameCollection(String nameCollection) {
        this.nameCollection = nameCollection;
    }

    public String getSlugCollection() {
        return slugCollection;
    }

    public void setSlugCollection(String slugCollection) {
        this.slugCollection = slugCollection;
    }

    public String getImgCollection() {
        return imgCollection;
    }

    public void setImgCollection(String imgCollection) {
        try {
            this.imgCollection = Common.convertToBase64(imgCollection);
        } catch (IOException e) {
            this.imgCollection = imgCollection;
        }
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getProductName() {
        return ProductName;
    }
    public void setProductName(String productName) {
        ProductName = productName;
    }
}
