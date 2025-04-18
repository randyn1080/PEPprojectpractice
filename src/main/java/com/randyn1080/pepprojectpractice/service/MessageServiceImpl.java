package com.randyn1080.pepprojectpractice.service;

import com.randyn1080.pepprojectpractice.dao.MessageDAO;
import com.randyn1080.pepprojectpractice.dao.MessageDAOImpl;
import com.randyn1080.pepprojectpractice.model.Message;

import java.util.List;

public class MessageServiceImpl implements MessageService{
    private final MessageDAO messageDAO;
    private final AccountService accountService;

    public MessageServiceImpl() {
        this.messageDAO = new MessageDAOImpl();
        this.accountService = new AccountServiceImpl();
    }

    @Override
    public Message createMessage(Message msg) {
        if (msg.getMessage_text() == null ||
                msg.getMessage_text().isBlank() ||
                msg.getMessage_text().length() > 255) {
            return null;
        }
        // check if the posted by account exists
        if (accountService.accountExists(msg.getPosted_by()) == true) {
            return messageDAO.createMessage(msg);
        }

        return null;
    }

    @Override
    public Message getMessageById(int msgId) {
        return messageDAO.getMessageById(msgId);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageDAO.getAllMessagesByAccountId(accountId);
    }

    @Override
    public Message updateMessageText(int msgId, String newText) {
        Message currentMessage = messageDAO.getMessageById(msgId);
        if (currentMessage == null) {
            return null;
        }

        if (newText == null ||
                newText.isBlank() ||
                newText.length() > 255) {
            return null;
        }

        return messageDAO.updateMessageText(msgId, newText);
    }

    @Override
    public Message deleteMessage(int msgId) {
        return messageDAO.deleteMessage(msgId);
    }

}
