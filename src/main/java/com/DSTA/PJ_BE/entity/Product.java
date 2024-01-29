package com.DSTA.PJ_BE.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_product", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;
    @Column(name = "slug_product", columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String slug;
    @Column(name = "information", columnDefinition = "TEXT", nullable = false)
    private String information;
    @Column(name = "summary", columnDefinition = "TEXT", nullable = false)
    private String summary;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "image", columnDefinition = "VARCHAR(2048)", nullable = false)
    private String image;
    @Column(name = "stock", columnDefinition = "INT UNSIGNED", nullable = false)
    private Integer stock;
    @Column(name = "price", columnDefinition = "NUMERIC(10,2)", nullable = false)
    private BigDecimal price;
    @Column(name = "discounted_price", columnDefinition = "NUMERIC(10,2)", nullable = false)
    private BigDecimal  discountedPrice;
    @Column(name = "categories_id", columnDefinition = "BIGINT", nullable = false)
    private Long categoriesId;
    @Column(name = "brands_id", columnDefinition = "BIGINT", nullable = false)
    private Long brandsId;
    @Column(name = "status", columnDefinition = "VARCHAR(20)", nullable = false)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug.toUpperCase();
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public Long getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(Long brandsId) {
        this.brandsId = brandsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
