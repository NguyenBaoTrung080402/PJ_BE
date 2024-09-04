package com.DSTA.PJ_BE.dto.OrderDetail;

import com.DSTA.PJ_BE.dto.OrderV2.OrderV2Dto;
import com.DSTA.PJ_BE.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.DSTA.PJ_BE.entity.OrderDetail}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto implements Serializable {
    Long id;
    Product product;
    OrderV2Dto orderV2;
    Integer amount;
    Double priceAtPurchase;
    String color;
    String size;
}