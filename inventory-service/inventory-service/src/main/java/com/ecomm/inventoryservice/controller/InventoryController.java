package com.ecomm.inventoryservice.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.inventoryservice.entity.Inventory;
import com.ecomm.inventoryservice.entity.InventoryDto;
import com.ecomm.inventoryservice.entity.ProductSellerKey;
import com.ecomm.inventoryservice.service.InventoryService;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;






    @PostMapping("/inventory/addQuantity")
    public ResponseEntity<Inventory> addQuantity(@RequestBody InventoryDto inventory) {

        Inventory inventoryProduct = Inventory.builder().id(ProductSellerKey.builder().
                productId(inventory.getProductId())
                .sellerId(inventory.getSellerId())
                .build()).quantity(inventory.getQuantity()).build();
        try {
            inventoryService.addOrUpdateQuantity(inventoryProduct);
            return new ResponseEntity<>(
                    CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("inventory/decreaseQuantity")
    public ResponseEntity<?> deleteQuantity(@RequestBody InventoryDto inventory) {
        Inventory inventoryProduct = Inventory.builder().id(ProductSellerKey.builder().
                productId(inventory.getProductId())
                .sellerId(inventory.getSellerId())
                .build()).quantity(inventory.getQuantity()).build();
        try {
            inventoryService.decreaseQuantity(inventoryProduct);
            return new ResponseEntity<>(
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
