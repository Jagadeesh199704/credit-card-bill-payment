package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.HdfcTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HdfcTransactionDAO extends JpaRepository<HdfcTransaction,Integer> {

}