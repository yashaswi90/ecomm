package com.ecomm.ordermanagementservice.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetail {

    @Id
    private long id;
    private String accountName;
    private String accountNumber;
    private String ifscCode;
}
