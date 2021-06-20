package com.ecomm.inventoryservice.service;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecomm.inventoryservice.entity.Inventory;
import com.ecomm.inventoryservice.entity.ProductSellerKey;
import com.ecomm.inventoryservice.repository.InventoryRepository;

public class InventoryServiceImplTest {
    private InventoryServiceImpl underTest;

    @Mock
    InventoryRepository inventoryRepository;

    @Captor
    ArgumentCaptor<Inventory> inventoryArgumentCaptor;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new InventoryServiceImpl(inventoryRepository);
    }


    @Test
    public void shouldAddQuantity() {

        Inventory inventory = Inventory.builder().quantity(20)
                .id(ProductSellerKey.builder().sellerId(1L).productId(22L).build()).build();

        Mockito.when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));
        Mockito.when(inventoryRepository.save(ArgumentMatchers.any(Inventory.class))).thenReturn(inventory);

        underTest.addOrUpdateQuantity(inventory);
    }

    @Test
    public void shouldUpdateQuantity() {

        Inventory inventory = Inventory.builder().quantity(20)
                .id(ProductSellerKey.builder().sellerId(1L).productId(22L).build()).build();

        Mockito.when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.empty());
        Mockito.when(inventoryRepository.save(inventoryArgumentCaptor.capture())).thenReturn(inventory);

        underTest.addOrUpdateQuantity(inventory);

        Assert.assertThat(inventoryArgumentCaptor.getValue().getQuantity(), is(20));

    }


    @Test
    public void shouldDecreaseQuantity() {

        Inventory inventory = Inventory.builder().quantity(20)
                .id(ProductSellerKey.builder().sellerId(1L).productId(22L).build()).build();


        Inventory inventoryEntity = Inventory.builder().quantity(30)
                .id(ProductSellerKey.builder().sellerId(1L).productId(22L).build()).build();
        Mockito.when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.ofNullable(inventoryEntity));
        Mockito.when(inventoryRepository.save(inventoryArgumentCaptor.capture())).thenReturn(inventory);

        underTest.decreaseQuantity(inventory);

        Assert.assertThat(inventoryArgumentCaptor.getValue().getQuantity(), is(10));

    }
}