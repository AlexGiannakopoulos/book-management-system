package com.alex.library.bookmanagementsystem.repos;


import com.alex.library.bookmanagementsystem.model.Library;
import com.alex.library.bookmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepo extends JpaRepository<Library, Long> {

    public Library findByUser(User user);
}
