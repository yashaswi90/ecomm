package com.ecomm.ordermanagementservice.service;


import org.springframework.stereotype.Service;

import com.ecomm.ordermanagementservice.domain.OrderDto;

@Service
public interface OrderService {


    String createOrder(OrderDto orderDto);

}
