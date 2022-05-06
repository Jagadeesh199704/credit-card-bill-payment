package com.billpayment.creditcard.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HdfcTransactionResponse {

    private int hdfcTransactionId;

    private Date hdfcTransactionDate;
    private int hdfcTransactionAmount;
}