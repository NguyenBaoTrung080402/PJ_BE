package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.dto.Collections.CollectionsViewAllDtoInf;
import com.DSTA.PJ_BE.dto.Collections.CollectionsViewDetailsDtoInf;
import com.DSTA.PJ_BE.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query(value = "SELECT ca.id as id, ca.slug as slugCategory, ca.name_category as nameCategory, ca.image_category as imgCategory FROM categories ca ORDER BY ca.id DESC", nativeQuery = true)
    Page<CollectionsViewAllDtoInf> getALlCate(Pageable pageable);

    @Query(value = "SELECT ca FROM Collection ca WHERE ca.id = :id")
    Collection getCategoryByID(@Param("id") Long id);

    @Query(value = "SELECT ca.slug as slug, ca.name_category as name, ca.image_category as imageCategory FROM categories ca WHERE ca.id = :id", nativeQuery = true)
    CollectionsViewDetailsDtoInf getCategoryByIDByADmin(@Param("id") Long id);
}
