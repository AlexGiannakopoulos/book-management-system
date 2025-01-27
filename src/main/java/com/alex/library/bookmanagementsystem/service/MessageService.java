package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.model.Message;
import com.alex.library.bookmanagementsystem.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    public void insertMessage(Message message) {
        messageRepo.save(message);
    }

    public List<Message> getMyMessages(User appUser) {
        return messageRepo.findByReceiver(appUser);
    }
}
