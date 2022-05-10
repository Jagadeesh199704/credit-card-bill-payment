package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {


    @Query(value = "select * from user where user_email=?1 and user_password=?2",nativeQuery = true)
    Object login(String userEmail, String userPassword);


}