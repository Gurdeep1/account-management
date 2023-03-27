package com.test.accountmanagement.service;

import com.test.accountmanagement.model.Account;
import com.test.accountmanagement.pojo.AccountCreationReq;

import java.util.List;

public interface AccountService {
    void createAccount(AccountCreationReq accountCreationReq) throws Exception;

    Account getAccount(String accountId) throws Exception;

    List<Account> getAllActiveAccounts();

    void updateAccount(Account account);
}
