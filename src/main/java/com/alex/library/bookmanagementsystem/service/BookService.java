package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.model.Book;
import com.alex.library.bookmanagementsystem.repos.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllNotDisabledBooks() {

        return bookRepo.findAll()
                .stream().filter(b -> !b.isDisabledBook()).collect(Collectors.toList());
    }

    public Book getBookById(int id) {
        return bookRepo.findByBookid(id);
    }

    public void insertBook(Book book) {
        bookRepo.save(book);
    }

    public void updateBook(Book book) {
        bookRepo.save(book);
    }

    public void disableBookById(Integer bookid) {
        Book b = bookRepo.getReferenceById(bookid);
        b.setDisabledBook(true);
        bookRepo.save(b);
    }
}