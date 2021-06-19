package com.ecomm.cartservice.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;


    private String userId;
    private long productId;
    private long sellerId;
    private int quantity;
    private int price;

}
