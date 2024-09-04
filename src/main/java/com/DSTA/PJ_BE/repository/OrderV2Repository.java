package com.DSTA.PJ_BE.repository;

import com.DSTA.PJ_BE.entity.OrderV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderV2Repository extends JpaRepository<OrderV2, Long> {

    public Page<OrderV2> findByAccount_Id(Long accountId, Pageable pageable);
}