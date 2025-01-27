package com.alex.library.bookmanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    String bookname;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookid;

    @Positive(message = "Price must be a positive number")
    private float bookprice;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItemSet = new HashSet<>();

    @ColumnDefault("false")
    private boolean disabledBook;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Book() {
    }


    public Integer getBookid() {
        return bookid;
    }


    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }


    public String getBookname() {
        return bookname;
    }


    public void setBookname(String bookname) {
        this.bookname = bookname;
    }


    public float getBookprice() {
        return bookprice;
    }


    public void setBookprice(float bookprice) {
        this.bookprice = bookprice;
    }


    public Set<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }


    public void setOrderItemSet(Set<OrderItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
    }


    public boolean isDisabledBook() {
        return disabledBook;
    }


    public void setDisabledBook(boolean isdisabled) {
        this.disabledBook = isdisabled;
    }


    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", bookprice=" + bookprice +
                ", orderItemSet=" + orderItemSet +
                ", isdisabled=" + disabledBook +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookid, book.bookid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookid);
    }


}