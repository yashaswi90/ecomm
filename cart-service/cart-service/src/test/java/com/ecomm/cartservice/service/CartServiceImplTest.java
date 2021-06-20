package com.ecomm.cartservice.service;

import static org.hamcrest.Matchers.is;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecomm.cartservice.domain.CartDto;
import com.ecomm.cartservice.entity.Cart;
import com.ecomm.cartservice.entity.Items;
import com.ecomm.cartservice.repository.CartRepository;

public class CartServiceImplTest {

    private CartServiceImpl underTest;

    @Mock
    CartRepository cartRepository;

    @Mock
    RestTemplate restTemplate;

    @Captor
    ArgumentCaptor<Cart> cartentityArgumentCaptor;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CartServiceImpl(cartRepository, restTemplate);
    }


    @Test
    public void shouldAddItemToCart() {

        CartDto cartDto = CartDto.builder()
                .price(300)
                .productId(12)
                .quantity(2)
                .sellerId(987)
                .userId("UserId").build();

        Items item = Items.builder().quantity(2).price(200).sellerId(986).productId(1234).build();
        Set<Items> itemsArrayList = new HashSet<>();
        itemsArrayList.add(item);
        Cart cartResponse = Cart.builder().items(itemsArrayList).userId("UserID").cartId(1).build();
        Mockito.when(cartRepository.save(cartentityArgumentCaptor.capture())).thenReturn(cartResponse);

        ResponseEntity<Cart> cartResponseEntity = underTest.addItemToCart(cartDto);

        Assert.assertNotNull(cartResponseEntity);
        Assert.assertThat(cartResponseEntity.getStatusCode(), is(HttpStatus.CREATED));
    }

}