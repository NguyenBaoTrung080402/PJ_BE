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

    @Query(value = "SELECT co.id as id, co.slug as slugCollection, co.name_collection as nameCollection, co.image_collection as imgCollection, co.description as description, co.is_active as isActive, ca.name_category as categoryName, p.name_product as ProductName "
            +
            "FROM collection co " +
            "LEFT JOIN category ca ON co.category_id = ca.id " +
            "LEFT JOIN product p ON co.product_id = p.id " +
            "ORDER BY ca.id DESC", nativeQuery = true)
    Page<CollectionsViewAllDtoInf> getALlCollec(Pageable pageable);

    @Query(value = "SELECT co FROM Collection co WHERE co.id = :id")
    Collection getCollectionByID(@Param("id") Long id);

    @Query(value = "SELECT \r\n" + //
                "    co.slug as slug,\r\n" + //
                "    co.name_collection as name,\r\n" + //
                "    co.image_collection as imageCollection,\r\n" + //
                "    co.description as description,\r\n" + //
                "    co.is_active as isActive,\r\n" + //
                "    co.category_id as categoryId,\r\n" + //
                "    co.product_id as productId\r\n" + //
                "FROM \r\n" + //
                "    collection co\r\n" + //
                "WHERE \r\n" + //
                "    co.id = :id", nativeQuery = true)
    CollectionsViewDetailsDtoInf getCategoryByIDByADmin(@Param("id") Long id);
}
