package com.billpayment.creditcard.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int userId;
    private String userName;
    private String mobileNo;

    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;
}