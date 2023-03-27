package com.test.accountmanagement.controller;

import com.test.accountmanagement.pojo.AccountCreationReq;
import com.test.accountmanagement.pojo.PaymentsCreationReq;
import com.test.accountmanagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity createPayment(
            @RequestBody PaymentsCreationReq paymentsCreationReq) throws Exception {
        paymentService.createPayment(paymentsCreationReq);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllPayment() throws Exception {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/{user_account}")
    public ResponseEntity getAllPaymentForUSer(@PathVariable("user_account")String userAccount) throws Exception {
        return new ResponseEntity<>(paymentService.getAllPaymentForUser(userAccount), HttpStatus.OK);
    }

}
