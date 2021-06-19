package com.ecomm.cartservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items implements Serializable {
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/


    @Id
    private long productId;


   /* @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;*/


    private long sellerId;
    private int quantity;
    private long price;
}
