package com.alex.library.bookmanagementsystem.controllers;


import com.alex.library.bookmanagementsystem.model.Message;
import com.alex.library.bookmanagementsystem.model.dto.UserDto;
import com.alex.library.bookmanagementsystem.service.UserService;
import com.alex.library.bookmanagementsystem.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @GetMapping("/newmessage")
    public String newMessage(ModelMap model, HttpSession session) {
        Message message = new Message();

        // populate the receivers list
        UserDto loggedinuser = (UserDto) session.getAttribute("loggedinuser");
        List<UserDto> allusers = userService.getAllUsersDto();
        allusers.remove(loggedinuser);
        model.addAttribute("receivers", allusers);
        model.addAttribute("message", message);
        return "newmessageform";
    }

    @PostMapping("/insertmessage")
    public String insertMessage(@ModelAttribute Message message, ModelMap model,
                                HttpSession session, RedirectAttributes attributes) {
        UserDto loggedinuser = (UserDto) session.getAttribute("loggedinuser");

        message.setSender(userService.mapToEntity(loggedinuser));
        message.setSenddate(LocalDateTime.now());
        messageService.insertMessage(message);
        attributes.addFlashAttribute("infomessage", "Message sent successfully");
        return "redirect:/showdashboard";
    }
}
