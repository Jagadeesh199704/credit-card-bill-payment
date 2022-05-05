package com.billpayment.creditcard.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDetailRequest {


    private int userId;
    private String userName;
    private String mobileNo;
    private String userEmail;
    private String userPassword;
}