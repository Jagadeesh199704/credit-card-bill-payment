package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transaction where transaction_id=?1", nativeQuery = true)
    Transaction findDetailsByTransactionId(int transactionId);

}