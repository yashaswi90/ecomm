package com.ecomm.inventoryservice.entity;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    @NotNull
    @Size(max = 20)
    private Long productId;

    @NotNull
    @Size(max = 20)
    private Long sellerId;

    private int quantity;

}
