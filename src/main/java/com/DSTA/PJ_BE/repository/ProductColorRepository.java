package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
    @Query(value = "SELECT * FROM product_color WHERE product_id = :id", nativeQuery = true)
    ProductColor getProductColorByProductId(@Param("id") Long id);
}
