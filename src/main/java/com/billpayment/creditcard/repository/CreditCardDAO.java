package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardDAO extends JpaRepository<CreditCard,Integer> {


}