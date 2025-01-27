package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.model.Book;
import com.alex.library.bookmanagementsystem.model.Library;
import com.alex.library.bookmanagementsystem.model.Order;
import com.alex.library.bookmanagementsystem.model.OrderItem;
import com.alex.library.bookmanagementsystem.model.dto.OrderItemDto;
import com.alex.library.bookmanagementsystem.repos.BookRepo;
import com.alex.library.bookmanagementsystem.repos.OrderItemRepo;
import com.alex.library.bookmanagementsystem.repos.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final OrderItemRepo orderItemRepo;

    private final BookRepo bookRepo;
    private final InventoryService inventoryService;

    public OrderService(OrderRepo orderRepo, OrderItemRepo orderItemRepo, BookRepo bookRepo, InventoryService inventoryService) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.bookRepo = bookRepo;
        this.inventoryService = inventoryService;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getAllOrdersByLibrary(Library library) {
        return orderRepo.findByLibrary(library);
    }

    public Book getBookById(Integer id) {
        return bookRepo.getReferenceById(id);
    }

    public List<Order> getAllOrderForLibrary(Library library) {
        return orderRepo.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public int getInventoryForBookAndLibrary(Integer bookid, Library library) {
        Book book = bookRepo.getReferenceById(bookid);
        return inventoryService.fetchInventoryforBooks(library, book);
    }

    public float getOrdersCostTotalByLibrary(Library library) {
        return orderRepo.costTotal(library);

    }

    public OrderItem mapToEntity(OrderItemDto dto) {
        OrderItem item = new OrderItem();
        item.setBook(dto.getBook());
        item.setQuantity(dto.getQuantity());
        return item;
    }

    public List<OrderItemDto> removeFromOrderByBookId(List<OrderItemDto> orders, Integer bookid) {
        OrderItemDto tempdto = new OrderItemDto();
        for (OrderItemDto o : orders) {
            if (o.getBook().getBookid().equals(bookid)) {
                tempdto = o;
            }
        }
        orders.remove(tempdto);
        return orders;
    }

    public OrderItem getOrderItemById(Integer id) {
        return orderItemRepo.findById(id).get();
    }

    public void completeOrderItem(Integer id) {
        OrderItem temp = getOrderItemById(id);
        //TODO Update stock of library?
        temp.setDone(true);
        orderItemRepo.save(temp);
    }

}
