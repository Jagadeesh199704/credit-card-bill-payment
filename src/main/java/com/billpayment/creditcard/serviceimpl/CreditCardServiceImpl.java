package com.billpayment.creditcard.serviceimpl;

import com.billpayment.creditcard.dto.*;
import com.billpayment.creditcard.entity.*;
import com.billpayment.creditcard.repository.*;
import com.billpayment.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private HdfcTransactionDAO hdfcTransactionDAO;



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
     * method for fetching credit card detail
     * */
    @Override
    public ResponseEntity<BaseResponse> fetchCreditCardDetail(int creditCardId) {


        BaseResponse baseResponse = new BaseResponse();
        Optional<CreditCard> creditCard = creditCardDAO.findById(creditCardId);
        CreditCard card = creditCard.get();
        CreditCardDetail creditCardDetail = new CreditCardDetail();

        creditCardDetail.setCreditCardId(card.getCreditCardId());
        creditCardDetail.setCreditCardName(card.getCreditCardName());
        creditCardDetail.setCreditCardLimit(card.getCreditCardLimit());
        creditCardDetail.setCreditCardExpense(card.getCreditCardExpense());
        creditCardDetail.setCreditCardBalance(card.getCreditCardBalance());
        baseResponse.setMessage("credit card detail found");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setResponse(creditCardDetail);

        switch (creditCardId) {
            case 1:

                HdfcCreditCardResponse hdfcCreditCardResponse = new HdfcCreditCardResponse();
                hdfcCreditCardResponse.setHDFCCreditCardId(1);
                hdfcCreditCardResponse.setHDFCCreditCardBalance(60000);
                hdfcCreditCardResponse.setHDFCCreditCardLimit(100000);
                hdfcCreditCardResponse.setHDFCCreditCardName("HDFC");
                hdfcCreditCardResponse.setHDFCCreditCardExpense(500);
                baseResponse.setResponse(hdfcCreditCardResponse);
                baseResponse.setMessage(" HDFC credit card record found successfully");

                Optional<HdfcTransaction> transaction = hdfcTransactionDAO.findById(creditCardId);
                List<HdfcTransactionResponse> transactions = new ArrayList<>();
                HdfcTransactionResponse hdfcTransactionResponse = new HdfcTransactionResponse();
                for (HdfcTransactionResponse transactionHistory : transactions) {
                    hdfcTransactionResponse.setHdfcTransactionId(transaction.get().getHdfcTransactionId());
                    hdfcTransactionResponse.setHdfcTransactionAmount(transaction.get().getHdfcTransactionAmount());
                    hdfcTransactionResponse.setHdfcTransactionDate(transaction.get().getHdfcTransactionDate());
                    transactions.add(hdfcTransactionResponse);
                    baseResponse.setResponse(transactions);
                }
              /*  HdfcTransactionResponse hdfcTransactionResponse1 = new HdfcTransactionResponse();
                hdfcTransactionResponse1.setHdfcTransactionId(transaction.get().getHdfcTransactionId());
                hdfcTransactionResponse1.setHdfcTransactionAmount(transaction.get().getHdfcTransactionAmount());
                hdfcTransactionResponse1.setHdfcTransactionDate(transaction.get().getHdfcTransactionDate());
                baseResponse.setResponse(hdfcTransactionResponse1);
                transactions.add(hdfcTransactionResponse1);
                creditCardDetail.setHdfcTransactionResponses(transactions);*/
                baseResponse.setMessage("HDFC Transaction record found");
                baseResponse.setHttpStatus(HttpStatus.OK);
                baseResponse.setHttpStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);


            case 2:
                /*SbiCreditCardResponse sbiCreditCardResponse = new SbiCreditCardResponse();
                sbiCreditCardResponse.setSBICreditCardId(2);
                sbiCreditCardResponse.setSBICreditCardName("SBI");
                sbiCreditCardResponse.setSBICreditCardBalance(50000);
                sbiCreditCardResponse.setSBICreditCardExpense(4500);
                sbiCreditCardResponse.setSBICreditCardLimit(1000000);*/

                baseResponse.setMessage(" SBI credit card record found successfully");
                break;
            case 3:
                /*CANARACreditCardResponse canaraCreditCardResponse= new CANARACreditCardResponse();
                canaraCreditCardResponse.setCANARACreditCardId(3);
                canaraCreditCardResponse.setCANARACreditCardName("CANARA CARD");
                canaraCreditCardResponse.setCANARACreditCardBalance(25000);
                canaraCreditCardResponse.setCANARACreditCardLimit(70000);
                canaraCreditCardResponse.setCANARACreditCardExpense(23000);*/

                baseResponse.setMessage("CANARA BANK CREDIT CARD FOUND");

                break;
            default:
                baseResponse.setMessage("  No record found ");
        }
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
