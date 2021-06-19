package com.ecomm.cartservice.domain;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsDto {

    @Id
    private long cartId;

    @NonNull
    private String userId;

    @NonNull
    private long productId;

    @NonNull
    private long sellerId;

    @NonNull
    private int quantity;

    @NonNull
    private long price;

}
