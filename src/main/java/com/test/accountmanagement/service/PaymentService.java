package com.test.accountmanagement.service;

import com.test.accountmanagement.model.Payment;
import com.test.accountmanagement.pojo.PaymentsCreationReq;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentService {

    @Transactional
    void createPayment(PaymentsCreationReq paymentsCreationReq) throws Exception;

    List<Payment> getAllPayments();

    List<Payment> getAllPaymentForUser(String userAccount) throws Exception;
}
