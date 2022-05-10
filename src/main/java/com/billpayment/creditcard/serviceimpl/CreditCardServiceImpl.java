package com.billpayment.creditcard.serviceimpl;

import com.billpayment.creditcard.dto.*;
import com.billpayment.creditcard.entity.*;
import com.billpayment.creditcard.repository.*;
import com.billpayment.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class CreditCardServiceImpl implements CreditCardService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CreditCardDAO creditCardDAO;

    @Autowired
    private RegisterDAO registerDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    /*
     * post mapping for register user detail
     *
     * */

    @Override
    public ResponseEntity<BaseResponse> userDetailRequest(UserDetailRequest userDetailRequest) {

        User user = new User();

        user.setUserId(userDetailRequest.getUserId());
        user.setUserName(userDetailRequest.getUserName());
        user.setMobileNo(userDetailRequest.getMobileNo());
        user.setUserEmail(userDetailRequest.getUserEmail());
        user.setUserPassword(userDetailRequest.getUserPassword());
        userDAO.save(user);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("detail save successfully!!!");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    /*
     * post mapping for login
     * */
    @Override
    public ResponseEntity<BaseResponse> loginUser(UserDetailRequest userDetailRequest) {


        Optional<Object> user = Optional.ofNullable(userDAO.login(userDetailRequest.getUserEmail(), userDetailRequest.getUserPassword()));

        BaseResponse baseResponse = new BaseResponse();
        if (!user.isPresent()) {
            baseResponse.setMessage("user not found ");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());

            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
        }
        baseResponse.setMessage("user login successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    /*
     * method for fetching credit card transaction detail
     * */
    @Override
    public ResponseEntity<BaseResponse> fetchTransactionDetail(int transactionId) {
        BaseResponse baseResponse = new BaseResponse();


        List<Transaction> transactionList = Collections.singletonList(transactionDAO.findDetailsByTransactionId(transactionId));

        TransactionDetailRequest transactionDetailRequest = new TransactionDetailRequest();

        List<CreditCardDetail> creditCardDetails = new ArrayList<>();
        for(Transaction transactionHistory:transactionList){
            CreditCardDetail creditCardDetail = new CreditCardDetail();
            creditCardDetail.setCreditCardId(transactionHistory.getTransactionId());
            creditCardDetail.setCreditCardBalance(transactionHistory.getTransactionAmount());

            creditCardDetails.add(creditCardDetail);
        }

        transactionDetailRequest.setCreditCardDetails(creditCardDetails);

        baseResponse.setMessage("Transaction data found successfully");
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
/*
* payment
* */
    @Override
    public ResponseEntity<BaseResponse> fetchPaymentDetails(PaymentRequest paymentRequest) {
        int amount = 0;
        BaseResponse baseResponse = new BaseResponse();

        Optional<CreditCard> creditCard = creditCardDAO.findById(paymentRequest.getCreditCardId());
        if (!creditCard.isPresent()){
            baseResponse.setMessage("Card not found");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        }

        Transaction transaction = new Transaction();


        transaction.setTransactionAmount(paymentRequest.getPaymentAmount());
        transaction.setTransactionDate(new Date());
        transaction.setCreditCardId(paymentRequest.getCreditCardId());
        transactionDAO.save(transaction);

        amount = creditCard.get().getCreditCardBalance() + paymentRequest.getPaymentAmount();
        int updatedExpenses = creditCard.get().getCreditCardExpense() - paymentRequest.getPaymentAmount();
        creditCard.get().setCreditCardBalance(amount);
        creditCard.get().setCreditCardExpense(updatedExpenses);
        creditCardDAO.save(creditCard.get());

        baseResponse.setMessage("Payment Successful");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }
}
