package com.DSTA.PJ_BE.dto.ProductSizeDto;

public class ProductSizeGetDto {
    private String nameSize;
    private Long id;

    // getter and setter methods
    public String getNameSize() {
        return nameSize;
    }
    public void setNameSize(String nameSize) {
        this.nameSize = nameSize;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
