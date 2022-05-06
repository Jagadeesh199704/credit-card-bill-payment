package com.billpayment.creditcard.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CANARACreditCardResponse {

    private int CANARACreditCardId;
    private String CANARACreditCardName;
    private int CANARACreditCardBalance;
    private int CANARACreditCardLimit;
    private int CANARACreditCardExpense;


}