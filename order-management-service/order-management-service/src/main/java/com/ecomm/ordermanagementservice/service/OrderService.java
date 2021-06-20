package com.ecomm.ordermanagementservice.service;


import org.springframework.stereotype.Service;

import com.ecomm.ordermanagementservice.domain.OrderDto;
import com.ecomm.ordermanagementservice.entity.Order;

@Service
public interface OrderService {


    Order createOrder(OrderDto orderDto);

}
