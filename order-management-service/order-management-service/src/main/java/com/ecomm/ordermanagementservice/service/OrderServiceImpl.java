package com.ecomm.ordermanagementservice.service;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecomm.ordermanagementservice.domain.CartResponse;
import com.ecomm.ordermanagementservice.domain.OrderDto;
import com.ecomm.ordermanagementservice.entity.Item;
import com.ecomm.ordermanagementservice.entity.Order;
import com.ecomm.ordermanagementservice.repository.OrderRepository;
import com.ecomm.ordermanagementservice.utility.Utility;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderDto orderDto) {
        String userId = orderDto.getUserId();
        ResponseEntity<CartResponse> cartResponse = getCartDetails(orderDto, userId);

        Order order = createOrder(cartResponse.getBody().getItems(), cartResponse.getBody().getUserId());

        Order orderResponse = orderRepository.save(order);
        deleteCart(orderDto);

        return orderResponse;
    }

    private ResponseEntity<CartResponse> getCartDetails(OrderDto orderDto, String userId) {
        final URI uri = fromHttpUrl("http://localhost:8085/getCartDetail/" + userId)
                .queryParam("cartId", orderDto.getCartId())
                .build()
                .toUri();

        final RequestEntity<String> requestEntity = new RequestEntity<>(GET, uri);
        return restTemplate.exchange(requestEntity, new ParameterizedTypeReference<CartResponse>() {
        });
    }

    private void deleteCart(OrderDto orderDto) {
        final URI deleteUri = fromHttpUrl("http://localhost:8085/delete")
                .queryParam("cartId", orderDto.getCartId())
                .queryParam("userId", orderDto.getUserId())
                .build()
                .toUri();

        final RequestEntity<String> deleteRequestEntity = new RequestEntity<>(DELETE, deleteUri);
        restTemplate.exchange(deleteRequestEntity, new ParameterizedTypeReference<Void>() {
        });
    }


    private Order createOrder(Set<Item> cart, String userId) {
        ArrayList<Item> items = new ArrayList<>(cart);
        return Order.builder().item(cart)
                .userId(userId)
                .orderedDate(LocalDate.now())
                .status("PAYMENT_EXPECTED")
                .total(Utility.countTotalPrice(items))
                .build();
    }
}
