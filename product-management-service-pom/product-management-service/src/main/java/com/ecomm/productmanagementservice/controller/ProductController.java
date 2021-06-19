package com.ecomm.productmanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.productmanagementservice.entity.Product;
import com.ecomm.productmanagementservice.service.ProductService;

@RestController
public class ProductController {


    @Autowired
    ProductService productService;


    @PostMapping(value = "/products")
    private ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (product != null) {
            try {
                Product product1 = productService.addProduct(product);
                return new ResponseEntity<Product>(
                        product1,
                        HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Product>(
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Product>(
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/products/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.getProductByProductId(id);
        if (product != null) {
            try {
                productService.deleteProduct(id);
                return new ResponseEntity<>(
                        HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/products", params = "category")
    public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam("category") String category) {
        List<Product> products = productService.getAllProductByCategory(category);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(
                    products,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/products", params = "name")
    public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam("name") String name) {
        List<Product> products = productService.getAllProductsByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(
                    products,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }
}
