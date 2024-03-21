package com.DSTA.PJ_BE.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
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
    @Column(name = "status", columnDefinition = "VARCHAR(20) DEFAULT 'Processing'", nullable = false)
    private String status;
    @Column(name = "address", columnDefinition = "TEXT", nullable = false )
    private String address;
    @Column(name = "tel", columnDefinition = "VARCHAR(10)", nullable = false)
    private String tel;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
