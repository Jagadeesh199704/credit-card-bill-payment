package com.billpayment.creditcard.serviceimpl;

import com.billpayment.creditcard.dto.BaseResponse;
import com.billpayment.creditcard.dto.RegisterDetailRequest;
import com.billpayment.creditcard.dto.UserDetailRequest;
import com.billpayment.creditcard.entity.Register;
import com.billpayment.creditcard.entity.User;
import com.billpayment.creditcard.repository.CreditCardDAO;
import com.billpayment.creditcard.repository.RegisterDAO;
import com.billpayment.creditcard.repository.UserDAO;
import com.billpayment.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }

    /*
     * post mapping for login
     * */
    @Override
    public ResponseEntity<BaseResponse> loginUser( UserDetailRequest userDetailRequest) {



        Optional<Object> user= Optional.ofNullable( userDAO.login(userDetailRequest.getUserEmail(),userDetailRequest.getUserPassword()));

        BaseResponse baseResponse = new BaseResponse();
        if (!user.isPresent()) {
            baseResponse.setMessage("user not found ");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.NOT_FOUND);
        }
        baseResponse.setMessage("user login successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
/*
        User user = new User();
        user.setUserId(userDetailRequest.getUserId());
        user.setUserName(userDetailRequest.getUserName());
        user.setUserEmail(userDetailRequest.getUserEmail());
        user.setUserPassword(userDetailRequest.getUserPassword());
        user.setMobileNo(userDetailRequest.getMobileNo());
        userDAO.save(user);*/
       /* BaseResponse baseResponse1=new BaseResponse();
        baseResponse1.setMessage("user data save successfully");
        baseResponse1.setHttpStatus(HttpStatus.OK);
        baseResponse1.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse1,HttpStatus.OK);*/



     /*   if (registerEmail == userEmail && registerPassword == userPassword)
        {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("user login successfully");
            baseResponse.setHttpStatus(HttpStatus.OK);
            baseResponse.setHttpStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }
*/
    }
}