package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.dto.ProductSizeDto.ProductSizeGetIdInf;
import com.DSTA.PJ_BE.entity.ProductSize;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    @Query(value = "SELECT * FROM product_size pz WHERE pz.product_id = :id", nativeQuery = true)
    ProductSize getProductSizeByProductId(@Param("id") Long id);

    @Query(value = "SELECT pz.id, s.size_name as nameSize FROM product_size pz JOIN size s ON pz.size_id = s.id WHERE pz.product_id = :id", nativeQuery = true)
    List<ProductSizeGetIdInf> getSizeProductID(Long id);

}
