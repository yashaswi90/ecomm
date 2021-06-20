package com.ecomm.ordermanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.ordermanagementservice.domain.OrderDto;
import com.ecomm.ordermanagementservice.entity.Order;
import com.ecomm.ordermanagementservice.service.OrderService;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/createOrder")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        try {
            String order = orderService.createOrder(orderDto);
            return new ResponseEntity<>(
                    order,
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Order>(
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity getOrderStatus(@PathVariable Long orderId) {
        try {
            Order order = orderService.getOrder(orderId);
            return new ResponseEntity<>(
                    order,
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Order>(
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}