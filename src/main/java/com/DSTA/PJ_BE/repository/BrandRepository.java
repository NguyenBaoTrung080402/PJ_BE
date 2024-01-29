package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query(value = "SELECT b FROM Brand b")
    List<Brand> getAllBrand();
    @Query(value = "SELECT b FROM Brand b WHERE b.id = :id")
    Brand getBrandById(@Param("id") Long id);
}
