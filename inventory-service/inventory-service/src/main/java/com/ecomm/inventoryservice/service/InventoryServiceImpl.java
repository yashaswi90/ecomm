package com.ecomm.inventoryservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.inventoryservice.entity.Inventory;
import com.ecomm.inventoryservice.entity.ProductSellerKey;
import com.ecomm.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void addOrUpdateQuantity(Inventory inventory) {
        ProductSellerKey id = inventory.getId();
        int newQuantity = 0;
        Optional<Inventory> inventoryPresent = inventoryRepository.findById(id);
        if (inventoryPresent.isPresent()) {
            newQuantity = inventoryPresent.get().getQuantity() + inventory.getQuantity();

        } else {
            newQuantity = inventory.getQuantity();
        }
        inventoryRepository.save(Inventory.builder().id(id).quantity(newQuantity).build());
    }

    @Override
    public void decreaseQuantity(Inventory inventory) {
        ProductSellerKey id = inventory.getId();
        int deletedQuantity = 0;
        Optional<Inventory> inventoryPresent = inventoryRepository.findById(id);
        if (inventoryPresent.isPresent()) {
            deletedQuantity = Math.abs( inventoryPresent.get().getQuantity() - inventory.getQuantity());

        }
        inventoryRepository.save(Inventory.builder().id(id).quantity(deletedQuantity).build());

    }
}
