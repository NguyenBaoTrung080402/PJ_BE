package com.DSTA.PJ_BE.dto.WishListDto;

import java.io.IOException;
import java.math.BigDecimal;

import com.DSTA.PJ_BE.utils.Common;

public class WishListViewDto {
    private Long id;
    private String nameProduct;
    private BigDecimal priceProduct;
    private Integer quantityProduct;
    private String image;
    private BigDecimal total;
    private String color;
    private String size;
    private BigDecimal discountedPrice;

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
