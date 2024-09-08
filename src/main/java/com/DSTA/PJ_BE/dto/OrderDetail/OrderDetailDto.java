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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderV2Dto getOrderV2() {
        return orderV2;
    }

    public void setOrderV2(OrderV2Dto orderV2) {
        this.orderV2 = orderV2;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "id=" + id +
                ", product=" + product +
                ", orderV2=" + orderV2 +
                ", amount=" + amount +
                ", priceAtPurchase=" + priceAtPurchase +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}