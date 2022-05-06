package com.billpayment.creditcard.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class HdfcTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int HdfcTransactionId;

    private Date HdfcTransactionDate;
    private int HdfcTransactionAmount;
}