package com.ecomm.cartservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartDto {
        private long cartId;


    private String userId;
    private long productId;
    private long sellerId;
    private int quantity;
    private int price;

}
