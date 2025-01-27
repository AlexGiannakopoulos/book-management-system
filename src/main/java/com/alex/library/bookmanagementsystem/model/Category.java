package com.alex.library.bookmanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "categoryname", length = 100)
    private String categoryname;

    @OneToMany(mappedBy = "category")
    private Set<Book> books = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setMedicines(Set<Book> books) {
        this.books = books;
    }

}