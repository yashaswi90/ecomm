package com.ecomm.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.inventoryservice.entity.Inventory;
import com.ecomm.inventoryservice.entity.ProductSellerKey;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, ProductSellerKey> {
}
