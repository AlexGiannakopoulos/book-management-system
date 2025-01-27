package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

    User findByUsernameAndUserpassword(String username, String userpassword);

    User findByUsername(String username);
}