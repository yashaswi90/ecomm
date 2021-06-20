package com.ecomm.ordermanagementservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequest {

    private Long orderId;
    private PaymentDetail paymentDetail;
}
