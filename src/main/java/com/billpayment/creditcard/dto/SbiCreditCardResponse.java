package com.billpayment.creditcard.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SbiCreditCardResponse {


    private int SBICreditCardId;
    private String SBICreditCardName;
    private int SBICreditCardBalance;
    private int SBICreditCardLimit;
    private int SBICreditCardExpense;
}