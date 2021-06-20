package com.ecomm.ordermanagementservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDto {

    private Long productId;

    private Long sellerId;

    private int quantity;
}
