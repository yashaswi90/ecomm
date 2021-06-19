package com.ecomm.cartservice.service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecomm.cartservice.domain.CartDetails;
import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Cartentity;
import com.ecomm.cartservice.entity.Items;
import com.ecomm.cartservice.repository.CartRepository;

@Service
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
    public Optional<Long> getCartDetailsByUserId(String userId) {
        return cartRepository.getByUserId(userId);
    }

    @Override
    public void updateCartItem(CartDto item) {

        getCartDetails(item.getCartId());

    }


    void getCartDetails(Long cartId) {
        cartRepository.getById(cartId);

    }
}
