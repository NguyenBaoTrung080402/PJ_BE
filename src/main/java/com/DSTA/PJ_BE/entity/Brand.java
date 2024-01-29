package com.DSTA.PJ_BE.entity;

import javax.persistence.*;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_brand", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nameBrand;
    @Column(name = "slug_brand", columnDefinition = "VARCHAR(50)", nullable = false)
    private String slugBrand;
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

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getSlugBrand() {
        return slugBrand;
    }

    public void setSlugBrand(String slugBrand) {
        this.slugBrand = slugBrand.toUpperCase();
    }
}
