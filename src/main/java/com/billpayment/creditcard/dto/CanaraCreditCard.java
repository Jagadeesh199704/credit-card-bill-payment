package com.billpayment.creditcard.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CanaraCreditCard {

    private int canaraCreditCardId;
    private String canaracreditCardName;
    private int canaracreditCardNumber;
    private String canaracreditCardExpiryDate;
    private double canaracreditCardBalance;
    private double canaracreditCardLimit;
    private double canaracreditCardExpense;


}
