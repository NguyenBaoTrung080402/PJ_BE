package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.Order.OderViewDto;
import com.DSTA.PJ_BE.dto.Order.OrderUpdateDto;
import com.DSTA.PJ_BE.dto.Order.OrderViewInfDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.Order;
import com.DSTA.PJ_BE.entity.WishList;
import com.DSTA.PJ_BE.repository.OrderRepository;
import com.DSTA.PJ_BE.repository.ProductRepository;
import com.DSTA.PJ_BE.repository.WishListRepository;
import com.DSTA.PJ_BE.service.OrderService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderServiceImp.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishListRepository wishListRepository;

    @Override
    @Transactional
    public DataResponse createOrder(Order order) {
        log.debug("Request Create Order");
        DataResponse res = new DataResponse();
        try {
            Account account = Common.getCurrentUserLogin();
            List<WishList> wishList = wishListRepository.getWLbyUserID(account.getId());

            if (wishList == null || wishList.isEmpty()) {
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            for (WishList wishListItem : wishList) {

                order.setUserId(account.getId());
                order.setProductId(wishListItem.getProductId());
                order.setQuantity(wishListItem.getQuantity());
                order.setAddress(account.getAddress());
                order.setTel(account.getTel());
                order.setStatus("Processing");
                order.setPrice(wishListItem.getPrice());
                orderRepository.save(order);

                // Xóa sản phẩm khỏi wishlist
                wishListRepository.removeFromWishList(wishListItem.getId());
            }

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.ADD_SUCCESS);
            res.setResult(order);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse updateStatus(Long id, OrderUpdateDto orderUpdateDto) {
        log.debug("Request Update Status");
        DataResponse res = new DataResponse();
        try {
            Order order = orderRepository.getOrderByID(id);
            if(order == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            order.setStatus(orderUpdateDto.getStatus());
            orderRepository.save(order);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.UPDATE_SUCCESS);
            res.setResult(order);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getAllOrder(Pageable pageable) {
        log.debug("Request Get ALl Order");
        DataResponse res = new DataResponse();
        try {
            Page<OrderViewInfDto> listOrder = orderRepository.getAllOrderInf(pageable);
            if(!listOrder.hasContent()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }
            Page<OderViewDto> order = Common.mapPage(listOrder, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(order);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getOrder() {
        log.debug("Request Get All Order");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            List<OrderViewInfDto> listOdered = orderRepository.getOrdered(account.getId());
            if(listOdered == null || listOdered.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            List<OderViewDto> result = Common.mapList(listOdered, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getOrderProcessing() {
        log.debug("Request Get Order Processing");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            List<OrderViewInfDto> listOdered = orderRepository.getOrderedProcessing(account.getId());
            if(listOdered == null || listOdered.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            List<OderViewDto> result = Common.mapList(listOdered, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;

        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getOrderShipping() {
        log.debug("Request Get Order Shipping");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            List<OrderViewInfDto> listOdered = orderRepository.getOrderedShipping(account.getId());
            if(listOdered == null || listOdered.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            List<OderViewDto> result = Common.mapList(listOdered, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;

        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getOrderConfirmed() {
        log.debug("Request Get Order Confirmed");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            List<OrderViewInfDto> listOdered = orderRepository.getOrderedConfirmed(account.getId());
            if(listOdered == null || listOdered.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            List<OderViewDto> result = Common.mapList(listOdered, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;

        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getOrderDelivered() {
        log.debug("Request Get Order Delivered");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            List<OrderViewInfDto> listOdered = orderRepository.getOrderedDelivered(account.getId());
            if(listOdered == null || listOdered.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            List<OderViewDto> result = Common.mapList(listOdered, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;

        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getOrderCanceled() {
        log.debug("Request Get Order Canceled");
        DataResponse res = new DataResponse();
        Account account = Common.getCurrentUserLogin();
        try {
            List<OrderViewInfDto> listOdered = orderRepository.getOrderedCanceled(account.getId());
            if(listOdered == null || listOdered.isEmpty()){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.LIST_NOT_FOUND);
                return res;
            }

            List<OderViewDto> result = Common.mapList(listOdered, OderViewDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(result);
            return res;

        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

}
