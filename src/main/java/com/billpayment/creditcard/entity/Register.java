package com.billpayment.creditcard.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int registerId;
    private String registerName;
    private String registerMobileNo;
    private String registerEmail;
    private String registerPassword;



}