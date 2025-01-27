package com.alex.library.bookmanagementsystem.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "inventory_warehouse")
public class InventoryWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", nullable = false)
    private Long inventoryid;

    @Column(name = "dateofcount")
    private LocalDate dateofcount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private Book book;

    @Column(name = "quantity")
    private Integer quantity;

    public Long getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(Long id) {
        this.inventoryid = id;
    }

    public LocalDate getDateofcount() {
        return dateofcount;
    }

    public void setDateofcount(LocalDate dateofcount) {
        this.dateofcount = dateofcount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book bookid) {
        this.book = bookid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}