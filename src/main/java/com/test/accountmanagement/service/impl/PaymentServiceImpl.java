package com.test.accountmanagement.service.impl;

import com.test.accountmanagement.enums.PaymentType;
import com.test.accountmanagement.model.Account;
import com.test.accountmanagement.model.Payment;
import com.test.accountmanagement.pojo.PaymentsCreationReq;
import com.test.accountmanagement.repository.PaymentsRepository;
import com.test.accountmanagement.service.AccountService;
import com.test.accountmanagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentsRepository paymentsRepository;
    private final AccountService accountService;

    @Autowired
    public PaymentServiceImpl(PaymentsRepository paymentsRepository, AccountService accountService) {
        this.paymentsRepository = paymentsRepository;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public void createPayment(PaymentsCreationReq paymentsCreationReq) throws Exception {
        Account useAccount = accountService.getAccount(paymentsCreationReq.getAccount());
        if(ObjectUtils.isEmpty(useAccount)){
            throw new Exception("payer account invalid");
        }
        Account toAccount = accountService.getAccount(paymentsCreationReq.getToAccount());
        if(ObjectUtils.isEmpty(toAccount)){
            throw new Exception("payee account invalid");
        }
        if(paymentsCreationReq.getPaymentType().equals(PaymentType.DEBIT)){
            if(useAccount.getBalance() < paymentsCreationReq.getAmount()){
                throw new Exception("account balance not sufficient");
            }
            useAccount.setBalance(useAccount.getBalance() - paymentsCreationReq.getAmount());
            toAccount.setBalance(toAccount.getBalance() + paymentsCreationReq.getAmount());
        }
        else {
            if(paymentsCreationReq.getAccount().equals(paymentsCreationReq.getToAccount())){
                useAccount.setBalance(useAccount.getBalance() + paymentsCreationReq.getAmount());
            }
            else {
                if (toAccount.getBalance() < paymentsCreationReq.getAmount()) {
                    throw new Exception("account balance not sufficient in sender account");
                }
                useAccount.setBalance(useAccount.getBalance() + paymentsCreationReq.getAmount());
                toAccount.setBalance(toAccount.getBalance() - paymentsCreationReq.getAmount());
            }
        }
        accountService.updateAccount(toAccount);
        accountService.updateAccount(useAccount);
        Payment payment = Payment.builder()
                .accountId(paymentsCreationReq.getAccount())
                .toAccount(paymentsCreationReq.getToAccount())
                .paymentType(paymentsCreationReq.getPaymentType().name())
                .amount(paymentsCreationReq.getAmount())
                .createdAt(new Date())
                .modifiedAt(new Date())
                .build();
        paymentsRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments(){
        return paymentsRepository.findAll();
    }

    @Override
    public List<Payment> getAllPaymentForUser(String userAccount) throws Exception {
        Account account = accountService.getAccount(userAccount);
        if(ObjectUtils.isEmpty(account)){
            throw new Exception("account not exist");
        }
        return paymentsRepository.findByAccountId(userAccount);
    }
}
