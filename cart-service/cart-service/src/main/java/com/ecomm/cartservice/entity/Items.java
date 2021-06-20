package com.ecomm.cartservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long item_id;

    private long productId;

    private long sellerId;
    private int quantity;
    private long price;
}
