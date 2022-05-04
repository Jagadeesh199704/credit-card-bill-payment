package com.billpayment.creditcard.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegisterDetailRequest {

    private int registerId;
    private String registerName;
    private String registerMobileNo;
    private String registerEmail;
    private String registerPassword;


}