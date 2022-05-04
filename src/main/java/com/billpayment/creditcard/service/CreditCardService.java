package com.billpayment.creditcard.service;


import com.billpayment.creditcard.dto.BaseResponse;
import com.billpayment.creditcard.dto.RegisterDetailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardService {
    ResponseEntity<BaseResponse> registerUserDetail(RegisterDetailRequest registerDetailRequest);
}