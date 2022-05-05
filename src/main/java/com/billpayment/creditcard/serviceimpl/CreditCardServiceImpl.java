package com.billpayment.creditcard.serviceimpl;

import com.billpayment.creditcard.dto.BaseResponse;
import com.billpayment.creditcard.dto.RegisterDetailRequest;
import com.billpayment.creditcard.entity.Register;
import com.billpayment.creditcard.repository.CreditCardDAO;
import com.billpayment.creditcard.repository.RegisterDAO;
import com.billpayment.creditcard.repository.UserDAO;
import com.billpayment.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CreditCardDAO creditCardDAO;

    @Autowired
    private RegisterDAO registerDAO;



    @Override
    public ResponseEntity<BaseResponse> registerUserDetail(RegisterDetailRequest registerDetailRequest) {

        Register register = new Register();

        register.setRegisterId(registerDetailRequest.getRegisterId());
        register.setRegisterName(registerDetailRequest.getRegisterName());
        register.setRegisterMobileNo(registerDetailRequest.getRegisterMobileNo());
        register.setRegisterEmail(registerDetailRequest.getRegisterEmail());
        register.setRegisterPassword(registerDetailRequest.getRegisterPassword());

        registerDAO.save(register);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("detail save successfully!!!");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        baseResponse.setResponse(registerDetailRequest);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
}