package com.DSTA.PJ_BE.dto.WishListDto;

import java.math.BigDecimal;

public class WishListAddDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private String nameSize;
    private String nameColor;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNameSize() {
        return nameSize;
    }
    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }
    public String getNameColor() {
        return nameColor;
    }
    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
}
