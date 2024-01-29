package com.DSTA.PJ_BE.dto.WishListDto;

import java.math.BigDecimal;

public interface WishListDtoItf {
    Long getId();
    String getNameProduct();
    BigDecimal getPriceProduct();
    Integer getQuantityProduct();
    String getImage();
    BigDecimal getTotal();
}
