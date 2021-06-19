package com.ecomm.cartservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Cartentity;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Optional<Cart> getByUserId(String userId);
}
