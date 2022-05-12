package com.billpayment.creditcard.service;


import com.billpayment.creditcard.dto.BaseResponse;
import com.billpayment.creditcard.dto.PaymentRequest;
import com.billpayment.creditcard.dto.UserDetailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardService {
    ResponseEntity<BaseResponse> userDetailRequest(UserDetailRequest userDetailRequest);

    ResponseEntity<BaseResponse> fetchCreditCard();

    ResponseEntity<BaseResponse> loginUser( UserDetailRequest userDetailRequest);

    ResponseEntity<BaseResponse> fetchTransactionDetail(int creditId);

    ResponseEntity<BaseResponse> fetchPaymentDetails(PaymentRequest paymentRequest);


}