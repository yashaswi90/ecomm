package com.ecomm.cartservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Cartentity;
import com.ecomm.cartservice.service.CartService;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/addToCart")
    public ResponseEntity<Cartentity> addItemInCart(@RequestBody CartDto item) {
        Optional<Long> cartId = cartService.getCartDetailsByUserId(item.getUserId());
        if (cartId.isPresent()) {
            item.setCartId(cartId.get());
            cartService.updateCartItem(item);
        } else {
            cartService.addItemToCart(item);
        }
        return null;
    }


    @PostMapping("/addToCartOne")
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartDto item) {

        Optional<Long> cartId = cartService.getCartDetailsByUserId(item.getUserId());
        if (cartId.isPresent()) {
            return cartService.addItemToCart(item);
        }
        return null;
    }
}
