package com.ecomm.cartservice.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecomm.cartservice.domain.CartDetails;
import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Cartentity;

@Service
public interface CartService {


    ResponseEntity<Cart> addItemToCart(CartDto item);

    Optional<Long> getCartDetailsByUserId(String userId);

    void updateCartItem(CartDto item);
}
