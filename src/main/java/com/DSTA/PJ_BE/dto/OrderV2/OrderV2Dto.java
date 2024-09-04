package com.DSTA.PJ_BE.dto.OrderV2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.DSTA.PJ_BE.entity.OrderV2}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderV2Dto implements Serializable {
    Long id;
    String note;
    LocalDateTime orderDate;
    String status;
    String paymentMethod;

}