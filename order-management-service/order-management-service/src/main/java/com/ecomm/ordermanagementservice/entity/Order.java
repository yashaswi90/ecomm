package com.ecomm.ordermanagementservice.entity;

import static javax.persistence.CascadeType.ALL;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ecomm.ordermanagementservice.domain.PaymentDetail;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
@Builder
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "ordered_date")
    @NotNull
    private LocalDate orderedDate;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "status")
    @NotNull
    private String status;

    @Column(name = "total")
    private BigDecimal total;


    @OneToMany(cascade = {ALL})
    @JoinColumn(name = "order_id")
    private Set<Item> item;


    @OneToOne(cascade = {ALL})
    @JoinColumn(name = "order_id")
    private PaymentDetail paymentDetail;

    @NotNull
    private String userId;

}
