package com.ecomm.cartservice.service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.domain.UpdateCartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Items;
import com.ecomm.cartservice.repository.CartRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<Cart> addItemToCart(CartDto item) {
        Set<Items> itemsSet = createItemSet(item);
        Cart cartEntity = Cart.builder().userId(item.getUserId())
                .items(itemsSet).build();
        Cart save = cartRepository.save(cartEntity);

        ResponseEntity<Cart> cartResponseEntity = new ResponseEntity<>(save, HttpStatus.CREATED);
        return cartResponseEntity;
    }


    @Override
    public Optional<Cart> getCartDetailsByUserId(String userId) {
        return cartRepository.getByUserId(userId);
    }

    @Override
    public ResponseEntity<Cart> updateCartItem(UpdateCartDto item) {

        try {
            Cart cartItems = null;
            Optional<Cart> cart = getCartDetails(item.getCartId());
            Cart save = saveItemToCart(item, cartItems, cart);
            return new ResponseEntity<Cart>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    INTERNAL_SERVER_ERROR);
        }

    }

    private Cart saveItemToCart(UpdateCartDto item, Cart cartItems, Optional<Cart> cart) {
        if (cart.isPresent()) {
            cartItems = cart.get();
            for (Items items : cartItems.getItems()) {
                if (items.getProductId() == item.getProductId()) {
                    int newQuantity = items.getQuantity() + item.getQuantity();
                    items.setQuantity(newQuantity);
                } else {
                    addItemToCart(item, cartItems);
                }
            }
        }
        return cartRepository.save(cartItems);
    }

    @Override
    public ResponseEntity<Cart> getCartDetailsByCartId(Long cartId) {
        Optional<Cart> cartDetails = getCartDetails(cartId);
        try {
            if (cartDetails.isPresent()) {
                return new ResponseEntity<Cart>(cartDetails.get(), HttpStatus.OK);
            } else {
                throw new RuntimeException("Cart is Empty");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void deleteCart(Long cartId, String userId) {
        cartRepository.deleteById(cartId);

    }

    @Override
    public Cart getCartDetailsByCartId(String cartId, String userId) {
        Long cartId1 = Long.valueOf(cartId);
        Cart byCartIdAndUserId = cartRepository.findByCartIdAndUserId(cartId1, userId);
        return new ResponseEntity<Cart>(byCartIdAndUserId, HttpStatus.OK).getBody();
    }


    Optional<Cart> getCartDetails(Long cartId) {
        return cartRepository.findById(cartId);

    }


    private Set<Items> createItemSet(CartDto item) {
        Items items = Items.builder().price(item.getPrice())
                .quantity(item.getQuantity())
                .sellerId(item.getSellerId())
                .productId(item.getProductId()).build();
        Set<Items> itemsSet = new HashSet<>();
        itemsSet.add(items);
        return itemsSet;
    }

    private void addItemToCart(UpdateCartDto item, Cart cartItems) {
        Items itemAdd = Items.builder().productId(item.getProductId())
                .sellerId(item.getSellerId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
        cartItems.getItems().add(itemAdd);
    }
}
