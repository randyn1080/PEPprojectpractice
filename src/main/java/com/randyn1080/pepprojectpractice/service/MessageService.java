package com.randyn1080.pepprojectpractice.service;

import com.randyn1080.pepprojectpractice.model.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(Message message);
    List<Message> getAllMessages();
    List<Message> getAllMessagesByAccountId(int accountId);
    Message getMessageById(int msgId);
    Message updateMessageText(int msgId, String newText);
    Boolean deleteMessage(int msgId);
}
