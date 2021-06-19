package com.ecomm.inventoryservice.service;


import org.springframework.stereotype.Service;

import com.ecomm.inventoryservice.entity.Inventory;

@Service
public interface InventoryService {

    void addOrUpdateQuantity(Inventory inventory);
    void decreaseQuantity(Inventory inventory);

}
