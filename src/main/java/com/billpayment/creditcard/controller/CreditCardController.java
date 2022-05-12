package com.billpayment.creditcard.controller;

import com.billpayment.creditcard.dto.BaseResponse;
import com.billpayment.creditcard.dto.PaymentRequest;
import com.billpayment.creditcard.dto.UserDetailRequest;
import com.billpayment.creditcard.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    /*
     * post mapping for register user detail
     *
     * */


    @PostMapping("register-user-detail")
    public ResponseEntity<BaseResponse> registerUserDetail(@RequestBody UserDetailRequest userDetailRequest) {
        if(userDetailRequest == null || userDetailRequest.getMobileNo().isEmpty()) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("registration detail can not be empty");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return creditCardService.userDetailRequest(userDetailRequest);
    }

    /*
     * post mapping for login user
     *
     * */

    @PostMapping("user-login")
    public ResponseEntity<BaseResponse> loginUser(  @RequestBody UserDetailRequest userDetailRequest)
    {
        if (userDetailRequest==null)
        {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("please enter valid credentials");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.loginUser(userDetailRequest);
    }

    /*
     * Get mapping for fetching credit card detail
     *
     * */

    @GetMapping("fetch-transaction-detail/{creditId}")
    public ResponseEntity<BaseResponse> fetchCreditCardDetail(@PathVariable int creditId)
    {
        if (creditId==0 || String.valueOf(creditId) == null)
        {
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setMessage("Transaction id cannot be null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.fetchTransactionDetail(creditId);
    }

    /**
     *Post mapping for payment
     * @param paymentRequest
     * @return
     */
    @PostMapping("payment-details")
    public ResponseEntity<BaseResponse> fetchPaymentDetail(@RequestBody PaymentRequest paymentRequest)
    {
        if (paymentRequest.getPaymentAmount() ==0 || paymentRequest.getPaymentType() == null)
        {
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setMessage("Payment amount cannot be Zero");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.fetchPaymentDetails(paymentRequest);
    }

    /*
    * fetch credit card details
    * */
    @GetMapping("fetch-creditCards")
    public ResponseEntity<BaseResponse> fetchCreditCards()
    {

        return creditCardService.fetchCreditCard();
    }



}