package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.dto.OrderDetail.OrderDetailDto;
import com.DSTA.PJ_BE.dto.OrderV2.OrderV2Dto;
import com.DSTA.PJ_BE.service.OrderV2Service;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-v2")
public class OrderV2Controller {

    private final Logger log = LoggerFactory.getLogger(OrderV2Controller.class);

    @Autowired
    OrderV2Service orderV2Service;

    @PostMapping("/create-order")
    public DataResponse createOrder(@RequestBody List<OrderDetailDto> orderDetailDtoList) {
        log.debug("Controller Request Create Order");

        DataResponse res = orderV2Service.createOrder(orderDetailDtoList);

        return res;
    }

    @GetMapping("/get-order-user")
    public DataResponse getOrderUser(@PageableDefault(page = 0, size = 6) Pageable pageable) {
        log.debug("Controller Request Get Order User");

        DataResponse dataResponse = orderV2Service.getAllOrderByUserId(pageable);

        return dataResponse;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-order")
    public DataResponse getAllOrderAdmin(@PageableDefault(
            page = 0,
            size = 6,
            sort = "orderDate",
            direction = Sort.Direction.ASC) Pageable pageable) {

        log.debug("Controller Request Get All Order Admin");

        DataResponse dataResponse = orderV2Service.getAllOrder(pageable);

        return dataResponse;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-one-order/{orderId}")
    public DataResponse getOneOrderAdmin(@PathVariable("orderId") Long orderId) {

        log.debug("Controller Request Get One Order Admin");

        DataResponse dataResponse = orderV2Service.getOneOrder(orderId);

        return dataResponse;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-order-status/{orderId}")
    public DataResponse updateOrderStatusAdmin(@PathVariable("orderId") Long orderId, @RequestBody OrderV2Dto orderV2Dto) {

        log.debug("Controller Request Update Status Order Admin");

        String status = orderV2Dto.getStatus();
        DataResponse dataResponse = orderV2Service.updateStatusOrder(orderId, status);

        return dataResponse;
    }

}
