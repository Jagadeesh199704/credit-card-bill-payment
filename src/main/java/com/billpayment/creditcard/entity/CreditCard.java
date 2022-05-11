package com.billpayment.creditcard.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int creditCardId;

    private String creditCardName;

    private int creditCardBalance;

    private int creditCardExpense;

    private int creditCardLimit;




    @ManyToOne()
    private User user;


}