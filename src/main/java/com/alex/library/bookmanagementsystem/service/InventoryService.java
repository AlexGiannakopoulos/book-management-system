package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.model.Inventory;
import com.alex.library.bookmanagementsystem.model.InventoryWarehouse;
import com.alex.library.bookmanagementsystem.model.Library;
import com.alex.library.bookmanagementsystem.model.Book;
import com.alex.library.bookmanagementsystem.repos.InventoryRepo;
import com.alex.library.bookmanagementsystem.repos.InventoryWarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {


    private final InventoryRepo inventoryRepo;
    private final InventoryWarehouseRepo inventoryWarehouseRepo;

    public InventoryService(InventoryRepo inventoryRepo, InventoryWarehouseRepo inventoryWarehouseRepo) {
        this.inventoryRepo = inventoryRepo;
        this.inventoryWarehouseRepo = inventoryWarehouseRepo;
    }

    public List<Inventory> getLibraryInventory(Library library) {
        return inventoryRepo.findByLibraryOrderByDateofcount(library);
    }

    public int fetchInventoryforBooks(Library library, Book book) {
        return inventoryRepo.fetchInventoryByBookAndLibrary(library,book);
    }

    public List<InventoryWarehouse> getInventoryWarehouses() {
        return inventoryWarehouseRepo.findAll();
    }
}