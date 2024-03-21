package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    @Query(value = "SELECT * FROM product_size WHERE product_id = :id", nativeQuery = true)
    ProductSize getProductSizeByProductId(@Param("id") Long id);
}
