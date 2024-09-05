package com.DSTA.PJ_BE.entity;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_Category", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nameCategory;
    @Column(name = "slug_Category", columnDefinition = "VARCHAR(50)", nullable = false)
    private String slugCategory;
    @Column(name = "status", columnDefinition = "VARCHAR(10) DEFAULT 'Active'")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getSlugCategory() {
        return slugCategory;
    }

    public void setSlugCategory(String slugCategory) {
        this.slugCategory = slugCategory.toUpperCase();
    }
}
