package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.OrderDetail.OrderDetailDto;
import com.DSTA.PJ_BE.dto.OrderV2.OrderV2Dto;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderV2Service {
    public DataResponse createOrder(List<OrderDetailDto> orderDetailDtoList);

    public DataResponse getAllOrderByUserId(Pageable pageable);

    public DataResponse getAllOrder(Pageable pageable);

    public DataResponse getOneOrder(Long orderId);

    public DataResponse updateStatusOrder(Long orderId, String status);
}
