package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transaction where credit_card_id=?1", nativeQuery = true)
    List<Transaction> findDetailsByCreditId(int creditId);

}