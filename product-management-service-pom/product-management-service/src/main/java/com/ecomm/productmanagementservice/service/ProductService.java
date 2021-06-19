package com.ecomm.productmanagementservice.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomm.productmanagementservice.entity.Product;

@Service
public interface ProductService {


    List<Product> getAllProductByCategory(String category);

    List<Product> getAllProductsByName(String name);

    Product addProduct(Product product);

    void deleteProduct(Long productId);


    Product getProductByProductId(Long id);
}
