package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Message;
import com.randyn1080.pepprojectpractice.util.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {
    @Override
    public Message createMessage(Message message) {
        String SQL = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectionUtil.getConnection();
            pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, message.getPosted_by());
            pstmt.setString(2, message.getMessage_text());
            pstmt.setLong(3, message.getTime_posted_epoch());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedMessageId = rs.getInt(1);
                message.setMessage_id(generatedMessageId);
                return message;
            }
        } catch (SQLException e) {
            //TODO: handle exception
        }
        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        return List.of();
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int accountId) {
        return List.of();
    }

    @Override
    public Message getMessageById(int id) {
        return null;
    }

    @Override
    public Boolean updateMessage(Message message) {
        return null;
    }

    @Override
    public Boolean deleteMessage(int id) {
        return null;
    }
}
