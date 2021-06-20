package com.ecomm.cartservice.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.domain.UpdateCartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.service.CartService;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addOrUpdateItemInCart(@RequestBody CartDto item) {
        Optional<Cart> cartId = cartService.getCartDetailsByUserId(item.getUserId());
        try {
            if (cartId.isPresent()) {
                Cart cartId1 = cartId.get();
                UpdateCartDto upda = UpdateCartDto.builder()
                        .productId(item.getProductId())
                        .cartId(cartId1.getCartId())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .sellerId(item.getSellerId()).build();
                return cartService.updateCartItem(upda);
            } else {
                return cartService.addItemToCart(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCart/{cartId}")
    public ResponseEntity<Cart> getCartDetails(@PathVariable Long cartId) {

        return cartService.getCartDetailsByCartId(cartId);
    }

    @GetMapping("/getCartDetail/{userId}")
    public Cart getCartDetails(@RequestParam String cartId, @PathVariable String userId) {

        return cartService.getCartDetailsByCartId(cartId, userId);
    }


    @DeleteMapping("/delete")
    public void getCart(@RequestParam Long cartId, @RequestParam String userId) {

        cartService.deleteCart(cartId, userId);
    }


}
