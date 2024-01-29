package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p")
    Page<Product> getAllProduct(Pageable pageable);
    @Query(value = "SELECT p FROM Product p WHERE p.id = :id")
    Product getProductByID(@Param("id") Long id);
}
