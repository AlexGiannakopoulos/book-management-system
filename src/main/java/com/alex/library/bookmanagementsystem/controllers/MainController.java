package com.alex.library.bookmanagementsystem.controllers;


import com.alex.library.bookmanagementsystem.AppScopeBean;
import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.model.dto.UserDto;
import com.alex.library.bookmanagementsystem.model.dto.DashboardDto;
import com.alex.library.bookmanagementsystem.service.UserService;
import com.alex.library.bookmanagementsystem.service.MessageService;
import com.alex.library.bookmanagementsystem.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AppScopeBean applicationScopeBean;
    @Autowired
    MessageService messageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String showMainPage(ModelMap mm) {
        mm.addAttribute("login", new LoginDto("", ""));
        return "login.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        applicationScopeBean.setNumberofusers(applicationScopeBean.getNumberofusers() - 1);
        return "redirect:/";
    }


    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("loginDto") LoginDto dto, ModelMap mm, HttpSession session) {
        UserDto loggedinuser = userService.dologin(dto.username, dto.userpass);
        if (loggedinuser == null) {
            mm.addAttribute("message", "Wrong username or password");
            return "login.html";
        } else {
            applicationScopeBean.setNumberofusers(applicationScopeBean.getNumberofusers() + 1);
            session.setAttribute("loggedinuser", loggedinuser);
            return "redirect:/showdashboard";
        }
    }

    @GetMapping("/showdashboard")
    public String showDashboard(ModelMap mm, HttpSession session) {

        DashboardDto ddto = new DashboardDto();
        UserDto loggedinuser = (UserDto) session.getAttribute("loggedinuser");
        ddto.setTotalorders(orderService.getAllOrderForLibrary(loggedinuser.getLibrary()).size());
        if (loggedinuser.getRole() == "LIBRARIAN") {
            ddto.setTotalcostoforders(orderService.getOrdersCostTotalByLibrary(loggedinuser.getLibrary()));
        }
        mm.addAttribute("mymessages",  messageService.getMyMessages(userService.mapToEntity(loggedinuser)));
        mm.addAttribute("dashboard", ddto);
        return "index";
    }

    @GetMapping("/register")
    public String showRegister(ModelMap mm, HttpSession session) {
        RegisterDto registerDto = new RegisterDto("","","","", "");
        mm.addAttribute("registerdto", registerDto);
        return "register";
    }

    @PostMapping("/doregister")
    public String doRegister(@ModelAttribute("registerdto") RegisterDto dto,
                             ModelMap mm,
                             RedirectAttributes redirectAttributes) {
        //Check passwords
        if(!dto.password1.equals(dto.password2)){
            mm.addAttribute("message", "Passwords do not match");
            return "register";
        }
        // Hash the first pass
        String hashedpass = passwordEncoder.encode(dto.password1);
        User user = userService.convertDtoToEntity(dto);
        user.setUserpassword(hashedpass);
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "User registered successfully. Please login");
        return "redirect:/";
    }

    private record LoginDto(String username, String userpass) {
    }

    public record RegisterDto(String username, String firstname, String lastname, String password1, String password2) {
    }

}