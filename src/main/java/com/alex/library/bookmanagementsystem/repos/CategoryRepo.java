package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}