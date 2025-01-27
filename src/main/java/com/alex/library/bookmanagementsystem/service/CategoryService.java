package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.model.Category;
import com.alex.library.bookmanagementsystem.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

}
