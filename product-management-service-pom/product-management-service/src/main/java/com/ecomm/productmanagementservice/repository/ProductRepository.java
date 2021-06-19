package com.ecomm.productmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.productmanagementservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByCategory(String category);

    List<Product> findAllByProductName(String name);

    void deleteByProductId(Long productId);



    Product findByProductId(Long id);
}
