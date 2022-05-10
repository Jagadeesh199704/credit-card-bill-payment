package com.billpayment.creditcard.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreditCardDetail {

    private int creditCardId;
    private String creditCardName;
    private int creditCardBalance;
    private int creditCardLimit;
    private int creditCardExpense;

}