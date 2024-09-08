package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.OrderDetail.OrderDetailDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.OrderDetail;
import com.DSTA.PJ_BE.entity.OrderV2;
import com.DSTA.PJ_BE.repository.OrderDetailRepository;
import com.DSTA.PJ_BE.repository.OrderV2Repository;
import com.DSTA.PJ_BE.service.OrderV2Service;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import com.DSTA.PJ_BE.utils.OrderStatus;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderV2ServiceImp implements OrderV2Service {
    private final Logger log = LoggerFactory.getLogger(OrderV2ServiceImp.class);

    @Autowired
    OrderV2Repository orderV2Repository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public DataResponse createOrder(List<OrderDetailDto> orderDetailDtoList) {
        log.debug("Request Create Order");

        DataResponse res = new DataResponse();

        OrderV2 newOrder = new OrderV2();
        Account account = Common.getCurrentUserLogin();

        newOrder.setAccount(account);
        newOrder = orderV2Repository.save(newOrder);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
            OrderDetail orderDetail = mapper.map(orderDetailDto, OrderDetail.class);
            orderDetail.setOrderV2(newOrder);
            orderDetailList.add(orderDetail);
        }

        orderDetailRepository.saveAll(orderDetailList);

        res.setStatus(Constants.SUCCESS);
        res.setMessage(Constants.ADD_SUCCESS);
        res.setResult(newOrder);

        return res;
    }

    @Override
    public DataResponse getAllOrderByUserId(Pageable pageable) {
        log.debug("Request Get All Order By User Id");

        DataResponse res = new DataResponse();

        Account account = Common.getCurrentUserLogin();
        Page<OrderV2> orderV2Page = orderV2Repository.findByAccount_Id(account.getId(), pageable);

        res.setStatus(Constants.SUCCESS);
        res.setResult(orderV2Page);

        return res;
    }

    @Override
    public DataResponse getAllOrder(Pageable pageable) {
        log.debug("Request Get All Order");

        DataResponse res = new DataResponse();

        Page<OrderV2> orders = orderV2Repository.findAll(pageable);

        res.setStatus(Constants.SUCCESS);
        res.setResult(orders);

        return res;
    }

    @Override
    public DataResponse getOneOrder(Long orderId) {
        log.debug("Request Get One Order");

        DataResponse res = new DataResponse();

        boolean checkOrderExist = orderV2Repository.existsById(orderId);
        if (checkOrderExist) {
            OrderV2 order = orderV2Repository.findById(orderId).get();

            res.setResult(order);
            res.setStatus(Constants.SUCCESS);

        } else {
            res.setStatus(Constants.NOT_FOUND);
            res.setMessage(Constants.NOT_FOUND_ORDER);
        }

        return res;
    }


    @Override
    public DataResponse updateStatusOrder(Long orderId, String status) {
        log.debug("Request Update Status Order");

        DataResponse res = new DataResponse();

        boolean checkOrderExist = orderV2Repository.existsById(orderId);
        if (checkOrderExist) {
            OrderV2 updateOrder = orderV2Repository.findById(orderId).get();

            String formattedStatus = OrderStatus.checkValidStatus(status);

            if (formattedStatus != null) {
                updateOrder.setStatus(status);
                orderV2Repository.save(updateOrder);
                res.setResult(updateOrder);
                res.setStatus(Constants.SUCCESS);
                res.setMessage(Constants.UPDATE_SUCCESS);
            } else {
                res.setResult(updateOrder);
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.ERROR_ORDER_STATUS);
            }

        } else {
            res.setStatus(Constants.NOT_FOUND);
            res.setMessage(Constants.NOT_FOUND_ORDER);
        }

        return res;
    }

}
