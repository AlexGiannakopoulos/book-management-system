package com.alex.library.bookmanagementsystem.service;


import com.alex.library.bookmanagementsystem.controllers.MainController;
import com.alex.library.bookmanagementsystem.model.Role;
import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.repos.UserRepo;
import com.alex.library.bookmanagementsystem.model.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo UserRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo UserRepo, PasswordEncoder passwordEncoder) {
        this.UserRepo = UserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto dologin(String username, String password) {
        User u = UserRepo.findByUsername(username);
        // Check if username is correct and passwords match
        if (u != null && passwordEncoder.matches(password, u.getUserpassword())) {
            return mapToDto(u);
        } else
            return null;

    }

    public UserDto mapToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setUsername(entity.getUsername());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setRole(entity.getRole().name());
        dto.setLibrary(entity.getLibrary());
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        return UserRepo.findById(dto.getUsername()).get();
    }

    public List<UserDto> getAllUsersDto() {
        return UserRepo.findAll().
                stream().
                map(this::mapToDto).
                collect(Collectors.toList());
    }

    public User convertDtoToEntity(MainController.RegisterDto dto) {
        User user = new User();
        user.setFirstname(dto.firstname());
        user.setRole(Role.MEMBER);
        user.setUsername(dto.username());
        user.setLastname(dto.lastname());
        return user;
    }

    public void createUser(User user) {
        UserRepo.save(user);
    }
}