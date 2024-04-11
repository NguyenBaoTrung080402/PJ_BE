package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.dto.Order.OrderUpdateDto;
import com.DSTA.PJ_BE.entity.Order;
import com.DSTA.PJ_BE.service.OrderService;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public DataResponse order(@RequestBody Order order){
        log.debug("Controller Request Order");
        DataResponse res = orderService.createOrder(order);
        return res;
    }

    @PutMapping("/update-status/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse updateStatus(@PathVariable("id") Long id,@RequestBody OrderUpdateDto orderUpdateDto){
        log.debug("Controller Request Update Status");
        DataResponse res = orderService.updateStatus(id, orderUpdateDto);
        return res;
    }

    @GetMapping("/get-all-order")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse getOrder(@PageableDefault(page = 0, size = 6) Pageable pageable){
        log.debug("Controller Request Get All Order");
        DataResponse res = orderService.getAllOrder(pageable);
        return res;
    }
//    con api dùng để hiển thị thông tin toàn bộ đơn hàng đã đặt của người dùng
    @GetMapping("/get-ordered")
    public DataResponse getOrdered(){
        log.debug("Controller Request Get All Order");
        DataResponse res = orderService.getOrder();
        return res;
    }

    @GetMapping("/get-order-processing")
    public DataResponse getOrderProcessing(){
        log.debug("Controller Request Get Order Processing");
        DataResponse res = orderService.getOrderProcessing();
        return res;
    }

    @GetMapping("/get-order-confirmed")
    public DataResponse getOrderConfirmed(){
        log.debug("Controller Request Get Order Confirmed");
        DataResponse res = orderService.getOrderConfirmed();
        return res;
    }

    @GetMapping("/get-order-shipping")
    public DataResponse getOrderShipping(){
        log.debug("Controller Request Get Order Shipping");
        DataResponse res = orderService.getOrderShipping();
        return res;
    }

    @GetMapping("/get-order-delivered")
    public DataResponse getOrderDelivered(){
        log.debug("Controller Request Get Order Delivered");
        DataResponse res = orderService.getOrderDelivered();
        return res;
    }

    @GetMapping("/get-order-canceled")
    public DataResponse getOrderCanceled(){
        log.debug("Controller Request Get Order Canceled");
        DataResponse res = orderService.getOrderCanceled();
        return res;
    }
}
