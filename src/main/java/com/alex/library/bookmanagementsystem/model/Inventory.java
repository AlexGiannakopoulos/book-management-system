package com.alex.library.bookmanagementsystem.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventory_id;

    @ManyToOne
    @JoinColumn(name = "libraryid")
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private Book book;

    private Integer quantity;

    @Temporal(TemporalType.DATE)
    private LocalDate dateofcount;

    public Inventory() {
    }

    public Long getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Long inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateofcount() {
        return dateofcount;
    }

    public void setDateofcount(LocalDate dateofcount) {
        this.dateofcount = dateofcount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}