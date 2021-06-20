package com.ecomm.cartservice.entity;


import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    private String userId;

    @OneToMany(cascade = {ALL})
    @JoinColumn(name = "cart_id")
    private Set<Items> items;
}
