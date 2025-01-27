package com.alex.library.bookmanagementsystem.repos;

import com.alex.library.bookmanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepo extends JpaRepository<Book, Integer> {

    public  Book findByBookid(Integer bookid);

}