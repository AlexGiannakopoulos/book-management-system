package com.alex.library.bookmanagementsystem.model;


import jakarta.persistence.*;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libraryid;
    private String libraryname;
    private String libraryaddress;
    private String librarycity;
    private Float lat;
    private Float lng;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public Library() {
    }

    public Library(Integer libraryId) {
        this.libraryid = libraryId;
    }

    public Integer getLibraryId() {
        return libraryid;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryid = libraryId;
    }

    public String getLibraryname() {
        return libraryname;
    }

    public void setLibraryname(String libraryName) {
        this.libraryname = libraryName;
    }

    public String getLibraryaddress() {
        return libraryaddress;
    }

    public void setLibraryaddress(String libraryAddress) {
        this.libraryaddress = libraryAddress;
    }

    public String getLibrarycity() {
        return librarycity;
    }

    public void setLibrarycity(String libraryCity) {
        this.librarycity = libraryCity;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}