package com.alex.library.bookmanagementsystem.controllers;


import com.alex.library.bookmanagementsystem.model.Order;
import com.alex.library.bookmanagementsystem.model.OrderItem;
import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.model.dto.OrderItemDto;
import com.alex.library.bookmanagementsystem.model.dto.UserDto;
import com.alex.library.bookmanagementsystem.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final UserService userService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private final OrderService orderService;
    private final InventoryService inventoryService;

    public OrderController(UserService userService, LibraryService libraryService, BookService bookService, OrderService orderService, InventoryService inventoryService) {
        this.userService = userService;
        this.libraryService = libraryService;
        this.bookService = bookService;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/insertorder")
    public String libraryorder(ModelMap modelMap, HttpSession session) {
        List<OrderItemDto> tempItemsList = (List<OrderItemDto>) session.getAttribute("tempItemsList");
        if (tempItemsList == null) {
            //if im calling the method for the 1st time
            tempItemsList = new ArrayList<>();
        }
        session.setAttribute("tempItemsList", tempItemsList);
        // Make one OrderItemDto and add it to the model
        OrderItemDto tempitem = new OrderItemDto();
        modelMap.addAttribute("tempitem", tempitem);
        // I need all the books for the dropdown
        modelMap.addAttribute("allbooks", bookService.getAllNotDisabledBooks());
        // Put the partially submitted items in the model
        return "libraryorder";
    }

    @PostMapping(value = "dopartialorder")
    public String doPartialOrder(@ModelAttribute OrderItemDto orderItem, HttpSession session) {
        // put the order item in the session of the user and return same page
        List<OrderItemDto> partialorder = (List<OrderItemDto>) session.getAttribute("tempItemsList");
        if (partialorder.contains(orderItem)) {//User already picked the medicine. Add the new quantity
            partialorder.remove(orderItem);
        }
        partialorder.add(orderItem);
        session.setAttribute("tempItemsList", partialorder);
        return "redirect:/insertorder";
    }

    @GetMapping("/orders")
    public String showallorders(ModelMap modelMap, HttpSession session) {
        User user = userService.mapToEntity((UserDto) session.getAttribute("loggedinuser"));
        if (user.getRole().name().equals("ADMIN")) {
            modelMap.addAttribute("allorders", orderService.getAllOrders());
        } else if (user.getRole().name().equals("LIBRARIAN")) {

            modelMap.addAttribute("allorders", orderService.getAllOrderForLibrary(user.getLibrary()));
        }
        //  modelMap.addAttribute("allorders", orderService.getAllOrders());
        return "allorders.html";
    }

    // Returns the current inventory for a specific medicine and pharmacy
    @GetMapping("/getinvformed")
    @ResponseBody
    public int getInventoryForMedicineAndPharmacy(@RequestParam int medId, HttpSession session) {
        User user = userService.mapToEntity((UserDto) session.getAttribute("loggedinuser"));
        int inv = inventoryService.fetchInventoryforBooks(user.getLibrary(), bookService.getBookById(medId));
        return inv;
    }

    @GetMapping(value = "/doinsertorder")
    public String doInsertOrder(HttpSession session) {
        // take all the order items from the session
        List<OrderItemDto> orderitems = (List<OrderItemDto>) session.getAttribute("tempItemsList");

        Order myorder = new Order();
        //convert OrderItemDtos to Entity
        List<OrderItem> tempitems = orderitems.stream()
                .map(o -> orderService.mapToEntity(o))
                .toList();

        // fix both sides of the relationship
        tempitems.forEach(i -> i.setOrder(myorder));
        myorder.setItems(tempitems);

        User user = userService.mapToEntity((UserDto) session.getAttribute("loggedinuser"));
        myorder.setLibrary(libraryService.getLibraryFromUser(user));
        myorder.setOrderdate(LocalDate.now());
        orderService.createOrder(myorder);
        session.setAttribute("tempItemsList", null); // clear the session
        return "redirect:/showdashboard";
    }

    @GetMapping("/deleteorderitem/{id}")
    public String deleteOrderItem(@PathVariable int id, HttpSession session) {
        List<OrderItemDto> orderitems = (List<OrderItemDto>) session.getAttribute("tempItemsList");
        List<OrderItemDto> newlist = orderService.removeFromOrderByBookId(orderitems, id);
        session.removeAttribute("tempItemsList");
        session.setAttribute("tempItemsList", newlist);
        return "redirect:/insertorder";
    }

    @GetMapping("/completeorderitem")
    public String completeOrderItem(@RequestParam int order) {
        orderService.completeOrderItem(order);
        return "redirect:/orders";
    }

}
