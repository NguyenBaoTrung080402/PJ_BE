package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.OrderDetail.OrderDetailDto;
import org.springframework.http.ResponseEntity;

public interface OrderDetailService {
    public ResponseEntity<?> addOrderDetail(OrderDetailDto orderDetailDto);

    public ResponseEntity<?> getAllOrderDetailByOrderId(Long orderId);

    public ResponseEntity<?> removeOrderDetail(Long orderDetailId);

    public ResponseEntity<?> updateOrderDetail(OrderDetailDto orderDetailDto);
}
