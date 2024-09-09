package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT b FROM Category b")
    List<Category> getAllBrand();
    @Query(value = "SELECT b FROM Category b WHERE b.id = :id")
    Category getBrandById(@Param("id") Long id);
}
