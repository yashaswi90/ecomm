package com.ecomm.productmanagementservice.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;


@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productname")
    @NotNull
    private String productName;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    @NotNull
    private String category;
}
