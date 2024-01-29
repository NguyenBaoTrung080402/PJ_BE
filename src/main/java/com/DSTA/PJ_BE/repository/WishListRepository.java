package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.dto.WishListDto.WishListDtoItf;
import com.DSTA.PJ_BE.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    @Query(value = "SELECT w FROM WishList w WHERE w.userId = :userId AND w.productId = :productId")
    List<WishList> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
    @Query(value = "SELECT p.name_product as nameProduct, p.price as priceProduct, wl.quantity as quantityProduct, wl.price as total, p.image as image , wl.id as id " +
            "FROM wish_list wl " +
            "JOIN product p ON wl.product_id = p.id " +
            "WHERE wl.user_id = :id", nativeQuery = true)
    List<WishListDtoItf> getAllWL(@Param("id") Long id);
    @Query(value = "SELECT wl FROM WishList wl WHERE wl.id = :id")
    WishList getWLbyID(@Param("id") Long id);

    @Query(value = "SELECT wl FROM WishList wl WHERE wl.userId = :id")
    List<WishList> getWLbyUserID(@Param("id") Long id);
    @Modifying
    @Query(value = "DELETE FROM WishList wl WHERE wl.id = :id ")
    void removeFromWishList(@Param("id") Long id);

    @Query(value = "SELECT wl FROM WishList wl WHERE wl.productId = :id")
    WishList getWLbyProdyctID(@Param("id") Long id);
}
