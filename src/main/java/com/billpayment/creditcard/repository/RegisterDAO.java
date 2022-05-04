package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDAO extends JpaRepository<Register,Integer> {


}