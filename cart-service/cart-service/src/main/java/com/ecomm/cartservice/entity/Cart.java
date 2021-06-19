package com.ecomm.cartservice.entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Cart {


    @Id
    private long cartId;

    private String userId;

    @OneToMany(mappedBy = "cart")
    private Set<Items> items;
}
