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
public class CANARATransactionResponse {
    private int cnrTransactionId;
    private Date cnrTransactionDate;
    private int cnrTransactionAmount;
}