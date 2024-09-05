package com.DSTA.PJ_BE.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_collection", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;
    @Column(name = "slug", columnDefinition = "VARCHAR(20)", nullable = false)
    private String slug;
    @Column(name = "image_collection", columnDefinition = "VARCHAR(200)", nullable = false)
    private String imageCollection;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isActive;
    @Column(name = "category_id", columnDefinition = "BIGINT", nullable = false)
    private Long categoryId;
    @Column(name = "product_id", columnDefinition = "BIGINT", nullable = false)
    private Long productIds;

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

    public String getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(String imageCollection) {
        this.imageCollection = imageCollection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductIds() {
        return productIds;
    }

    public void setProductIds(Long productIds) {
        this.productIds = productIds;
    }
}
