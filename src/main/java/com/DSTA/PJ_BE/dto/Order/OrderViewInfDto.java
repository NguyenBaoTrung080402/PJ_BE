package com.DSTA.PJ_BE.dto.Order;

public interface OrderViewInfDto {
    Long getId();
    String getProductName();
    String getTotal();
    String getUserName();
    Integer getQuantity();
    String getAddress();
    String getTel();
    String getStatus();
    String getImage();
}
