package com.alex.library.bookmanagementsystem.model;


import jakarta.persistence.*;

@Entity
public class User {

    @Id
    private String username;
    private String userpassword;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(mappedBy = "user")
    private Library library;


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String pharmacyName) {
        this.username = pharmacyName;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Library getLibrary() {
        return library;
    }

    public void setPharmacy(Library library) {
        this.library = library;
    }
}
