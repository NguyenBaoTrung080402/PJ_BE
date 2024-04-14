package com.DSTA.PJ_BE.dto.Order;

import com.DSTA.PJ_BE.utils.Common;

import java.io.IOException;

public class OderViewStatusDto {
    private Long id;
    private String productName;
    private String total;
    private Integer quantity;
    private String image;
    private String status;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
