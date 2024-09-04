package com.DSTA.PJ_BE.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

}