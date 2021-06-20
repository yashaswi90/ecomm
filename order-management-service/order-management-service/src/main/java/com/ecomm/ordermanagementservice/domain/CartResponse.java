package com.ecomm.ordermanagementservice.domain;

import java.util.Set;

import com.ecomm.ordermanagementservice.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private long cartId;

    private String userId;

    private Set<Item> items;

}
