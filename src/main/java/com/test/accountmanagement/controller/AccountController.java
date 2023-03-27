package com.test.accountmanagement.controller;

import com.test.accountmanagement.pojo.AccountCreationReq;
import com.test.accountmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createAccount(
            @RequestBody AccountCreationReq accountCreationReq) throws Exception {
        accountService.createAccount(accountCreationReq);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/{account_id}")
    public ResponseEntity getAccountByAccountId(@PathVariable("account_id") String accountId) throws Exception {
        return new ResponseEntity<>(accountService.getAccount(accountId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllActiveAccounts() throws Exception {
        return new ResponseEntity<>(accountService.getAllActiveAccounts(), HttpStatus.OK);
    }
}
