package com.test.accountmanagement.repository;

import com.test.accountmanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "select * from account where account_status = ?1 ", nativeQuery = true)
    List<Account> findAllAccount(String status);

    @Query(value = "select * from account where account_id = ?1 ", nativeQuery = true)
    Account getAccountByAccountId(String accountId);
}
