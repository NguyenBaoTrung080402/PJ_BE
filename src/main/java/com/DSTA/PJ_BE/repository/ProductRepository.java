package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.dto.Product.ProductGetAllInfDto;
import com.DSTA.PJ_BE.dto.Product.ProductGetByIdInfDto;
import com.DSTA.PJ_BE.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.id AS id, p.name_product as name, p.slug_product as slug, p.information as information, p.summary as summary," +
            "       p.description as description, p.image as image, p.stock as stock, p.price as price, p.discounted_price as discountedPrice," +
            "       p.status as status, c.name_category as categoriesName, b.name_brand as brandsName " +
            "FROM product p " +
            "JOIN categories c ON p.categories_id = c.id " +
            "JOIN brand b ON p.brands_id = b.id ORDER BY p.id ASC ", nativeQuery = true)
    Page<ProductGetAllInfDto> getAllProductInf(Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.id = :id")
    Product getProductByID(@Param("id") Long id);
    
    @Query(value = "SELECT p.name_product as name, p.slug_product as slug, p.information as information, p.summary as summary,"+
            "       p.description as description, p.image as image, p.stock as stock, p.price as price, p.discounted_price as discountedPrice,"+
            "       p.status as status, c.name_category as categoriesName, b.name_brand as brandsName " +
            "FROM product p " +
            "JOIN categories c ON p.categories_id = c.id " +
            "JOIN brand b ON p.brands_id = b.id " +
            "WHERE p.id = :id", nativeQuery = true)
    ProductGetByIdInfDto getProductByIDInf(@Param("id") Long id);
}
