package com.DSTA.PJ_BE.dto.OrderV2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.DSTA.PJ_BE.entity.OrderV2}
 */

@AllArgsConstructor
@NoArgsConstructor
public class OrderV2Dto implements Serializable {
    Long id;
    String note;
    LocalDateTime orderDate;
    String status;
    String paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String
    toString() {
        return "OrderV2Dto{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}