package com.ecomm.cartservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cartentity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartid;

    @NotNull
    private String userid;

    @NotNull
    private long productId;

    @NotNull
    private long sellerId;

    @NotNull
    private int quantity;

    private long price;


}
