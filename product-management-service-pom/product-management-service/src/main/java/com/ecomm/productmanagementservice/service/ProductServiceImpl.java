package com.ecomm.productmanagementservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.productmanagementservice.entity.Product;
import com.ecomm.productmanagementservice.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> getAllProductByCategory(String category) {
        List<Product> products = productRepository.findAllByCategory(category);
        return products;
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        List<Product> products = productRepository.findAllByProductName(name);
        return products;
    }


    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.delete(productRepository.findByProductId(productId));
    }

    @Override
    public Product getProductByProductId(Long id) {

        return productRepository.findByProductId(id);
    }
}
