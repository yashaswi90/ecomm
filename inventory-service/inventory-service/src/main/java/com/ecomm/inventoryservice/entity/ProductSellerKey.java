package com.ecomm.inventoryservice.entity;


import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSellerKey implements Serializable {
    @NotNull
    @Size(max = 20)
    private Long productId;

    @NotNull
    @Size(max = 20)
    private Long sellerId;


}



