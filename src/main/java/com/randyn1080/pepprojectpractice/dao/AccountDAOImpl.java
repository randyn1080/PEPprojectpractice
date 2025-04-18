package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Account;
import com.randyn1080.pepprojectpractice.util.ConnectionUtil;

import com.randyn1080.pepprojectpractice.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class AccountDAOImpl implements AccountDAO {
    private static final Logger logger = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Override
    public Account createAccount(Account account) {
        String sql = "INSERT INTO account (username, password) VALUES (?,?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedAccountId = rs.getInt(1);
                account.setAccount_id(generatedAccountId);
                logger.info("Account created with id: {}", generatedAccountId);
                return account;
            }

        } catch (SQLException e) {
            logger.error("Error creating account: {}", e.getMessage());
        } finally {
            DatabaseUtil.closeResource(rs);
            DatabaseUtil.closeResource(pstmt);
            DatabaseUtil.closeResource(connection);
        }

        return null;
    }

    @Override
    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, accountId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccount_id(accountId);
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                return account;
            }

        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            DatabaseUtil.closeResource(rs);
            DatabaseUtil.closeResource(pstmt);
            DatabaseUtil.closeResource(connection);
        }

        return null;
    }

    @Override
    public Account getAccountByUsername(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccount_id(rs.getInt("account_id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                return account;
            }

        } catch (SQLException e) {
            //TODO: handle exception
        } finally {
            DatabaseUtil.closeResource(rs);
            DatabaseUtil.closeResource(pstmt);
            DatabaseUtil.closeResource(connection);
        }

        return null;
    }


}