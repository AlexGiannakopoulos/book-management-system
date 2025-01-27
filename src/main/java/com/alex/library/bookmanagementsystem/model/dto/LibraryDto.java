package com.alex.library.bookmanagementsystem.model.dto;


public class LibraryDto {

    private String libraryName;
    private String libraryAddress;
    private String libraryCity;
    private float lat;
    private float lng;

    public LibraryDto(String libraryName, String libraryAddress, String libraryCity) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        this.libraryCity = libraryCity;
    }

    public LibraryDto() {
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    public String getLibraryCity() {
        return libraryCity;
    }

    public void setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {

        this.lng = lng;
    }

    @Override
    public String toString() {
        return "LibraryDto{" +
                "libraryName='" + libraryName + '\'' +
                ", libraryAddress='" + libraryAddress + '\'' +
                ", libraryCity='" + libraryCity + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}