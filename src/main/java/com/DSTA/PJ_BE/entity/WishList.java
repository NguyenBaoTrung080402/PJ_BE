package com.DSTA.PJ_BE.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wish_list")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", columnDefinition = "BIGINT", nullable = false)
    private Long userId;
    @Column(name = "product_id", columnDefinition = "BIGINT", nullable = false)
    private Long productId;
    @Column(name = "quantity", columnDefinition = "INT", nullable = false)
    private Integer quantity;
    @Column(name = "price", columnDefinition = "NUMERIC(10,2)", nullable = false)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
