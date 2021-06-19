package com.ecomm.cartservice.service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.domain.UpdateCartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Items;
import com.ecomm.cartservice.repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional

@Slf4j
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepository cartRepository;

    @Override
    public ResponseEntity<Cart> addItemToCart(CartDto item) {

        try {
            Items items = Items.builder().price(item.getPrice())
                    .quantity(item.getQuantity())
                    .sellerId(item.getSellerId())
                    .productId(item.getProductId()).build();
            Set<Items> itemsSet = new HashSet<>();
            itemsSet.add(items);
            Cart cartEntity = Cart.builder().userId(item.getUserId())
                    .items(itemsSet).build();


           /* Cartentity cartEntity = Cartentity.builder().userid(item.getUserId())
                    .sellerId(item.getSellerId())
                    .price(item.getPrice())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity()).build();*/

            Cart save = cartRepository.save(cartEntity);

            ResponseEntity<Cart> cartResponseEntity = new ResponseEntity<>(save, HttpStatus.CREATED);
            return cartResponseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    INTERNAL_SERVER_ERROR);
        }
    }

   /* @Override
    public ResponseEntity<Cartentity> addItemToCart(CartDetails item) {
        return null;
    }*/


    /*@Override
    public ResponseEntity<Cartentity> addItemToCart(CartDto item) {


        try {
            Cartentity cartEntity = Cartentity.builder().userid(item.getUserId())
                    .sellerId(item.getSellerId())
                    .price(item.getPrice())
                    .productId(item.getProductId())
                    .quantity(item.getQuantity()).build();

            Cartentity entity = cartRepository.save(cartEntity);
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    INTERNAL_SERVER_ERROR);
        }
    }*/

    @Override
    public Optional<Cart> getCartDetailsByUserId(String userId) {
        return cartRepository.getByUserId(userId);
    }

    @Override
    public ResponseEntity<Cart> updateCartItem(UpdateCartDto item) {

        try {
            Cart cart = getCartDetails(item.getCartId());
            for (Items items : cart.getItems()) {
                log.info("Calling get items....");

                if (items.getProductId() == item.getProductId()) {

                    log.info("Inside Check");
                    int newQuantity = items.getQuantity() + item.getQuantity();

                    items.setQuantity(newQuantity);
                } else {
                    log.info("Outsside check ");
                    Items itemAdd = Items.builder().productId(item.getProductId())
                            .sellerId(item.getSellerId())
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .build();
                    cart.getItems().add(itemAdd);
                }

            }
            Cart save = cartRepository.save(cart);

            return new ResponseEntity<Cart>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Cart> getCartDetailsByCartId(Long cartId) {
        Cart cartDetails = getCartDetails(cartId);
        return new ResponseEntity<Cart>(cartDetails, HttpStatus.OK);
    }


    Cart getCartDetails(Long cartId) {
        Cart existingCart = cartRepository.getById(cartId);
        return existingCart;

    }
}
