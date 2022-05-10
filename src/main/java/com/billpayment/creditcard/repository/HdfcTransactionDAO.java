package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.HdfcTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HdfcTransactionDAO extends JpaRepository<HdfcTransaction,Integer> {

    @Query(value = "SELECT * FROM hdfc_transaction",nativeQuery = true)
    Optional<HdfcTransaction> findByAll();
}