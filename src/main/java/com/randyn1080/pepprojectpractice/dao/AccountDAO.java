package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Account;

public interface AccountDAO {
    Account createAccount(Account account);
    Account getAccountById(int accountId);
    Account getAccountByUsername(String username);
}
