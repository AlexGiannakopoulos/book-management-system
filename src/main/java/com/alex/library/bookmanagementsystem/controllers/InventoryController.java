package com.alex.library.bookmanagementsystem.controllers;


import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.model.dto.UserDto;
import com.alex.library.bookmanagementsystem.service.UserService;
import com.alex.library.bookmanagementsystem.service.InventoryService;
import com.alex.library.bookmanagementsystem.service.LibraryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private LibraryService libraryService;


    @GetMapping("/getinventory")
    public String getAllInventory(ModelMap modelmap, HttpSession session) {
        User user = userService.mapToEntity((UserDto) session.getAttribute("loggedinuser"));
        modelmap.addAttribute("inventory",
                inventoryService.getLibraryInventory(libraryService.getLibraryFromUser(user)));
        return "inventorytable";
    }

    @GetMapping("/warehouseinventory")
    public String getWarehouseInventory(ModelMap modelmap, HttpSession session, Model model) {
        modelmap.addAttribute("inventory", inventoryService.getInventoryWarehouses());
        return "inventorytable";
    }
}
