package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Account;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public Account createAccount(Account account) {
        String sql = "INSERT INTO account (account_id, username, password) VALUES (?,?,?)";
    }


    @Override
    public Account getAccountById(int accountId) {
        return null;
    }
}
