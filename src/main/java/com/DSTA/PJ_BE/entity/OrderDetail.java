package com.DSTA.PJ_BE.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_v_2_id")
    private OrderV2 orderV2;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price_at_purchase")
    private Double priceAtPurchase;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

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

    public OrderV2 getOrderV2() {
        return orderV2;
    }

    public void setOrderV2(OrderV2 orderV2) {
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
        return "OrderDetail{" +
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