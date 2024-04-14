package com.DSTA.PJ_BE.dto.Product;

import java.io.IOException;
import java.math.BigDecimal;

import com.DSTA.PJ_BE.utils.Common;

public class ProductGetByIdDto {
    private String name;
    private String slug;
    private String information;
    private String summary;
    private String description;
    private String image;
    private Integer stock;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private Long categoriesId;
    private Long brandsId;
    private String status;

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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        try {
			this.image = Common.convertToBase64(image);
		} catch (IOException e) {
			this.image = image;
		}
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getCategoriesId() {
        return categoriesId;
    }
    
    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }
    
    public Long getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(Long brandsId) {
        this.brandsId = brandsId;
    }
}

