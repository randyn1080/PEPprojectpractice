package com.randyn1080.pepprojectpractice.service;

import com.randyn1080.pepprojectpractice.dao.MessageDAO;
import com.randyn1080.pepprojectpractice.dao.MessageDAOImpl;
import com.randyn1080.pepprojectpractice.model.Message;

import java.util.List;

public class MessageServiceImpl implements  MessageService {
    private final MessageDAO messageDAO;
    private final AccountService accountService;

    public MessageServiceImpl() {
        this.messageDAO = new MessageDAOImpl();
        this.accountService = new AccountServiceImpl();
    }

    @Override
    public Message createMessage(Message message) {
        if (message.getMessage_text() == null ||
                message.getMessage_text().isBlank() ||
                message.getMessage_text().length() > 255) {
            return null;
        }
        // check if the posted by account exists
        if (accountService.accountExists(message.getPosted_by()) == true) {
            return messageDAO.createMessage(message);
        }

        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int accountId) {
        return List.of();
    }

    @Override
    public Message getMessageById(int msgId) {
        return null;
    }

    @Override
    public Boolean updateMessage(Message message) {
        return null;
    }

    @Override
    public Boolean deleteMessage(int msgId) {
        return null;
    }
}
