package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.Inventory;
import com.alex.library.bookmanagementsystem.model.Book;
import com.alex.library.bookmanagementsystem.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    public List<Inventory> findByLibraryOrderByDateofcount(Library library);

    @Query("select  i.quantity from Inventory i where i.library = ?1 and i.book=?2" +
            " order by i.dateofcount limit 1")
    public int fetchInventoryByBookAndLibrary(Library library, Book book);

}