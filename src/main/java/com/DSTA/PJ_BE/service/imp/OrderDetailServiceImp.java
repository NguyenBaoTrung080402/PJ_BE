package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.OrderDetail.OrderDetailDto;
import com.DSTA.PJ_BE.repository.OrderRepository;
import com.DSTA.PJ_BE.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class OrderDetailServiceImp implements OrderDetailService {


    @Override
    public ResponseEntity<?> addOrderDetail(OrderDetailDto orderDetailDto) {


        return null;
    }

    @Override
    public ResponseEntity<?> getAllOrderDetailByOrderId(Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<?> removeOrderDetail(Long orderDetailId) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateOrderDetail(OrderDetailDto orderDetailDto) {
        return null;
    }
}
