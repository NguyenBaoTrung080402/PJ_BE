package com.DSTA.PJ_BE.dto.Product;

import java.math.BigDecimal;

public interface ProductGetByIdInfDto {
    public String getName();

    public String getSlug();

    public String getInformation();

    public String getSummary();

    public String getDescription();

    public String getImage();

    public Integer getStock();

    public BigDecimal getPrice();

    public BigDecimal getDiscountedPrice();

    public Long getCategoriesId();

    public Long getBrandsId();

    public String getStatus();
}
