package com.ecomm.ordermanagementservice.service;

import static com.ecomm.ordermanagementservice.constant.UrlConstants.CART_ID;
import static com.ecomm.ordermanagementservice.constant.UrlConstants.DELETE_CART;
import static com.ecomm.ordermanagementservice.constant.UrlConstants.GET_CART_DETAIL;
import static com.ecomm.ordermanagementservice.constant.UrlConstants.INVENTORY_DECREASE_QUANTITY;
import static com.ecomm.ordermanagementservice.constant.UrlConstants.USER_ID;
import static com.ecomm.ordermanagementservice.enumConstant.OrderStatus.PAYMENT_DONE;
import static com.ecomm.ordermanagementservice.enumConstant.OrderStatus.PAYMENT_EXPECTED;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ecomm.ordermanagementservice.domain.CartResponse;
import com.ecomm.ordermanagementservice.domain.InventoryRequestDto;
import com.ecomm.ordermanagementservice.domain.OrderDto;
import com.ecomm.ordermanagementservice.entity.Item;
import com.ecomm.ordermanagementservice.entity.Order;
import com.ecomm.ordermanagementservice.repository.OrderRepository;
import com.ecomm.ordermanagementservice.utility.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String createOrder(OrderDto orderDto) {
        String userId = orderDto.getUserId();
        ResponseEntity<CartResponse> cartResponse = getCartDetails(orderDto, userId);

        Order order = createOrder(cartResponse.getBody().getItems(), cartResponse.getBody().getUserId(), orderDto);
        Order orderResponse = saveOrder(order);
        String responseDeliveryDate = proceedPayment(orderResponse);

        deleteCart(orderDto);

        return responseDeliveryDate;
    }

    private String proceedPayment(Order orderResponse) {
        log.debug("Payment done ...........");
        orderResponse.setStatus("PAYMENT_DONE");
        orderRepository.save(orderResponse);

        orderResponse.getItem().stream().forEach(s -> {
            InventoryRequestDto inventoryRequestDto = InventoryRequestDto.builder().productId(s.getProductId())
                    .quantity(s.getQuantity())
                    .sellerId(s.getSellerId()).build();

            URI uri = fromHttpUrl(INVENTORY_DECREASE_QUANTITY)
                    .build()
                    .toUri();
            restTemplate.postForObject(uri, inventoryRequestDto, String.class);
            notifySeller(s.getSellerId(), s.getProductId());
        });

        return deliveryNotification(orderResponse.getOrderId());


    }

    private String deliveryNotification(Long orderId) {
        return String.valueOf(LocalDate.now().plusDays(7));
    }

    private void notifySeller(long sellerId, long productId) {
        log.debug("Packed the item now.....");
    }

    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    private ResponseEntity<CartResponse> getCartDetails(OrderDto orderDto, String userId) {
        final URI uri = fromHttpUrl(GET_CART_DETAIL + userId)
                .queryParam(CART_ID, orderDto.getCartId())
                .build()
                .toUri();

        final RequestEntity<String> requestEntity = new RequestEntity<>(GET, uri);
        return restTemplate.exchange(requestEntity, new ParameterizedTypeReference<CartResponse>() {
        });
    }

    private void deleteCart(OrderDto orderDto) {
        final URI deleteUri = fromHttpUrl(DELETE_CART)
                .queryParam(CART_ID, orderDto.getCartId())
                .queryParam(USER_ID, orderDto.getUserId())
                .build()
                .toUri();

        final RequestEntity<String> deleteRequestEntity = new RequestEntity<>(DELETE, deleteUri);
        restTemplate.exchange(deleteRequestEntity, new ParameterizedTypeReference<Void>() {
        });
    }


    private Order createOrder(Set<Item> cart, String userId, OrderDto orderDto) {
        ArrayList<Item> items = new ArrayList<>(cart);
        return Order.builder().item(cart)
                .userId(userId)
                .orderedDate(LocalDate.now())
                .paymentDetail(orderDto.getPaymentDetail())
                .address(orderDto.getAddress())
                .status("PAYMENT_EXPECTED")
                .total(Utility.countTotalPrice(items))
                .build();
    }
}
