package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findByReceiver(User receiver);
}