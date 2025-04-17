package com.randyn1080.pepprojectpractice.service;

import com.randyn1080.pepprojectpractice.model.Account;

public interface AccountService {
    Account registerUser(Account account);
    Account login(Account account);
    Boolean accountExists(int accountId);
}
