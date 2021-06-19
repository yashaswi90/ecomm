package com.ecomm.cartservice.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDetails {

    private long id;

    private String userId;

    private List<ItemDto> itemDtos;

}
