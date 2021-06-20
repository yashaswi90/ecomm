package com.ecomm.ordermanagementservice.domain;


import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
//    private long orderId;
    private long cartId;
    private String userId;
    private String address;

    private PaymentDetail paymentDetail;
//    private LocalDate orderedDate;
//    private String status;
//    private String address;
//    private BigDecimal total;


}
