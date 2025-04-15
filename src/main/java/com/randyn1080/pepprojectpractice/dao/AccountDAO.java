package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Account;

public interface AccountDAO {
    /**
     * Create account
     * @param account
     * @return
     */
    Account createAccount(Account account);

    /**
     * Read account
     * @param account
     * @return
     */
    Account getAccountById(Account account);
}
