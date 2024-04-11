package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.dto.ProductColorDto.ProductColorGetInf;
import com.DSTA.PJ_BE.entity.ProductColor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
    @Query(value = "SELECT * FROM product_color WHERE product_id = :id", nativeQuery = true)
    ProductColor getProductColorByProductId(@Param("id") Long id);

    @Query(value = "SELECT c.name as nameColor, pc.id as id FROM ProductColor pc JOIN Color c ON pc.colorId = c.id WHERE pc.productId = :id ")
    List<ProductColorGetInf> getColor(@Param("id") Long id);
}
