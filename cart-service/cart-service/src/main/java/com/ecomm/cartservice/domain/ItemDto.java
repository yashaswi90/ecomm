package com.ecomm.cartservice.domain;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    @Id
    private long productId;



    private long cartId;
    private long sellerId;
    private int quantity;
    private long price;

}
