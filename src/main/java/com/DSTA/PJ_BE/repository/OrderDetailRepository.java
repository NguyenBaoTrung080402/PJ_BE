package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}