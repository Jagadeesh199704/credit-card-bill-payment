package com.billpayment.creditcard.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BaseResponse {


    private String message;
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private Object response;

}