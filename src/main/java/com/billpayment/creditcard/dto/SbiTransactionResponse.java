package com.billpayment.creditcard.dto;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SbiTransactionResponse {


    private int sbiTransactionId;

    private Data sbiTransactionDate;
    private int sbiTransactionAmount;
}