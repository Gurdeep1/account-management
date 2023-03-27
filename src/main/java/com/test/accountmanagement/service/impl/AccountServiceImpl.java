package com.test.accountmanagement.service.impl;

import com.test.accountmanagement.enums.AccountStatus;
import com.test.accountmanagement.model.Account;
import com.test.accountmanagement.pojo.AccountCreationReq;
import com.test.accountmanagement.repository.AccountRepository;
import com.test.accountmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(AccountCreationReq accountCreationReq) throws Exception {
        Account existingAccount = accountRepository.getAccountByAccountId(accountCreationReq.getAccountId());
        if(!ObjectUtils.isEmpty(existingAccount)){
            throw new Exception("user already exist");
        }
        Account newAccount = Account.builder()
                .accountId(accountCreationReq.getAccountId())
                .balance(0d)
                .owner(accountCreationReq.getOwnerName())
                .currency(accountCreationReq.getCurrency())
                .accountStatus(AccountStatus.ACTIVE.name())
                .createdAt(new Date())
                .modifiedAt(new Date())
                .build();
        accountRepository.saveAndFlush(newAccount);
    }

    @Override
    public Account getAccount(String accountId) throws Exception {
        Account existingAccount = accountRepository.getAccountByAccountId(accountId);
        if(ObjectUtils.isEmpty(existingAccount)){
            throw new Exception("user does not exist");
        }
        return existingAccount;
    }

    @Override
    public List<Account> getAllActiveAccounts(){
        return accountRepository.findAllAccount(AccountStatus.ACTIVE.name());
    }

    @Override
    public void updateAccount(Account account){
        accountRepository.saveAndFlush(account);
    }
}
