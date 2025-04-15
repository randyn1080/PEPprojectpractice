package com.randyn1080.pepprojectpractice.dao;

import com.randyn1080.pepprojectpractice.model.Message;

import java.util.List;

public class MessageDAOImpl implements MessageDAO {
    @Override
    public Message createMessage(Message message) {
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
