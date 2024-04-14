package com.DSTA.PJ_BE.dto.Product;

import java.util.List;

import com.DSTA.PJ_BE.dto.ProductColorDto.ProductColorGetDto;
import com.DSTA.PJ_BE.dto.ProductSizeDto.ProductSizeGetDto;

public class ProductDetailDto {
    private ProductGetByIdDto product;
    private List<ProductSizeGetDto> sizes;
    private List<ProductColorGetDto> colors;
    // create constructor 
    public ProductDetailDto(ProductGetByIdDto product, List<ProductSizeGetDto> sizes, List<ProductColorGetDto> colors) {
        this.product = product;
        this.sizes = sizes;
        this.colors = colors;
    }

    // getters and setters
    public ProductGetByIdDto getProduct() {
        return product;
    }
    public void setProduct(ProductGetByIdDto product) {
        this.product = product;
    }
    public List<ProductSizeGetDto> getSizes() {
        return sizes;
    }
    public void setSizes(List<ProductSizeGetDto> sizes) {
        this.sizes = sizes;
    }
    public List<ProductColorGetDto> getColors() {
        return colors;
    }
    public void setColors(List<ProductColorGetDto> colors) {
        this.colors = colors;
    }
}
