package com.DSTA.PJ_BE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DSTA.PJ_BE.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "SELECT c FROM Color c")
    List<Color> getAll();
    
}
