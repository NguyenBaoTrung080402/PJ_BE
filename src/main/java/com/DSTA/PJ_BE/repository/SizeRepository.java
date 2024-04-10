package com.DSTA.PJ_BE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DSTA.PJ_BE.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>{
    @Query(value = "SELECT s FROM Size s")
    List<Size> getSize();

}
