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

    public String getCategoriesName();

    public String getBrandsName();

    public String getColorName();
    public String getSizeName();

    public String getStatus();
}
