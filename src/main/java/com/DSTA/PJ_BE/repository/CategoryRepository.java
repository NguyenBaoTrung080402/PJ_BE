package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    @Query(value = "SELECT ca FROM Categories ca")
    List<Categories> getALlCate();

    @Query(value = "SELECT ca FROM Categories ca WHERE ca.id = :id")
    Categories getCategoryByID(@Param("id") Long id);
}
