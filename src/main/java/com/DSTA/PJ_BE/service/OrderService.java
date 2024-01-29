package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.Order.OrderUpdateDto;
import com.DSTA.PJ_BE.entity.Order;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    DataResponse createOrder(Order order);

    DataResponse updateStatus(Long id, OrderUpdateDto orderUpdateDto);

    DataResponse getAllOrder(Pageable pageable);

    DataResponse getOrder();

    DataResponse getOrderProcessing();

    DataResponse getOrderShipping();

    DataResponse getOrderConfirmed();

    DataResponse getOrderDelivered();

    DataResponse getOrderCanceled();
}
