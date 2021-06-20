package com.ecomm.cartservice.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.domain.UpdateCartDto;
import com.ecomm.cartservice.entity.Cart;

@Service
@Transactional
public interface CartService {


    ResponseEntity<Cart> addItemToCart(CartDto item);

    Optional<Cart> getCartDetailsByUserId(String userId);

    ResponseEntity<Cart> updateCartItem(UpdateCartDto item);

    ResponseEntity<Cart> getCartDetailsByCartId(Long cartId);
//    void deleteItemFromCart(String cartId, Long productId);
}
