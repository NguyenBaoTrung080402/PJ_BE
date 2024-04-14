package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.dto.Order.OderViewStatusInfDto;
import com.DSTA.PJ_BE.dto.Order.OrderViewInfDto;
import com.DSTA.PJ_BE.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT w FROM Order w WHERE w.userId = :userId AND w.productId = :productId")
    List<Order> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query(value = "SELECT od FROM Order od WHERE od.id = :id")
    Order getOrderByID(@Param("id") Long id);

    @Query(value = "SELECT od.id AS id, p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, od.tel AS tel, od.address AS address, us.name AS userName " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id ", nativeQuery = true)
    Page<OrderViewInfDto> getAllOrderInf(Pageable pageable);

    @Query(value = "SELECT p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, od.tel AS tel, od.address AS address, us.name AS userName, p.image AS image " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id " +
            " WHERE od.user_id = :id", nativeQuery = true)
    List<OrderViewInfDto> getOrdered(@Param("id") Long id);

    @Query(value = "SELECT p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, p.image AS image " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id " +
            " WHERE od.user_id = :id AND od.status = 'Processing'", nativeQuery = true)
    List<OderViewStatusInfDto> getOrderedProcessing(@Param("id") Long id);

    @Query(value = "SELECT p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, p.image AS image " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id " +
            " WHERE od.user_id = :id AND od.status = 'Shipping'", nativeQuery = true)
    List<OderViewStatusInfDto> getOrderedShipping(@Param("id") Long id);
    @Query(value = "SELECT p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, p.image AS image " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id " +
            " WHERE od.user_id = :id AND od.status = 'Confirmed'", nativeQuery = true)
    List<OderViewStatusInfDto> getOrderedConfirmed(@Param("id") Long id);
    @Query(value = "SELECT p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, p.image AS image " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id " +
            " WHERE od.user_id = :id AND od.status = 'Delivered'", nativeQuery = true)
    List<OderViewStatusInfDto> getOrderedDelivered(@Param("id") Long id);
    @Query(value = "SELECT p.name_product AS productName, od.price AS total, od.status AS status, od.quantity AS quantity, p.image AS image " +
            " FROM orders od " +
            " JOIN product p ON od.product_id = p.id " +
            " JOIN user us ON od.user_id = us.id " +
            " WHERE od.user_id = :id AND od.status = 'Canceled'", nativeQuery = true)
    List<OderViewStatusInfDto> getOrderedCanceled(@Param("id") Long id);
}
