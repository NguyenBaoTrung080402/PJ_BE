package com.DSTA.PJ_BE.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_category", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;
    @Column(name = "slug", columnDefinition = "VARCHAR(20)", nullable = false)
    private String slug;
    @Column(name = "image_category", columnDefinition = "VARCHAR(200)", nullable = false)
    private String imageCategory;

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

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }
}
