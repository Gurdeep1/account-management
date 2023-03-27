package com.test.accountmanagement.repository;

import com.test.accountmanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    @Query(value = "select * from payment where account_id = ?1 ", nativeQuery = true)
    List<Payment> findByAccountId(String userAccount);

    @Query(value = "select * from payment ", nativeQuery = true)
    List<Payment> findAllPayments();
}
