package com.ecomm.ordermanagementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.ordermanagementservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
