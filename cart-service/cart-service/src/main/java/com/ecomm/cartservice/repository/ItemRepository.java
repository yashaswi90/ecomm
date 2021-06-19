package com.ecomm.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.cartservice.entity.Items;

public interface ItemRepository extends JpaRepository<Items, Long> {

}
