package com.alex.library.bookmanagementsystem.service;

import com.alex.library.bookmanagementsystem.model.Library;
import com.alex.library.bookmanagementsystem.model.User;
import com.alex.library.bookmanagementsystem.model.dto.LibraryDto;
import com.alex.library.bookmanagementsystem.repos.OrderRepo;
import com.alex.library.bookmanagementsystem.repos.LibraryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepo libraryRepo;
    private final OrderRepo orderRepo;


    public LibraryService(LibraryRepo libraryRepo, OrderRepo orderRepo) {
        this.libraryRepo = libraryRepo;
        this.orderRepo = orderRepo;
    }

    public Integer create(final LibraryDto libraryDto) {
        Library library = mapToEntity(libraryDto);
        return libraryRepo.save(library).getLibraryId();
    }

    public List<LibraryDto> getAllLibraries() {
        return libraryRepo.findAll().stream().map(library -> mapToDTO(library)).toList();

    }

    public Library getLibraryFromUser(User user) {
        return libraryRepo.findByUser(user);
    }

    public LibraryDto mapToDTO(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setLibraryAddress(library.getLibraryaddress());
        libraryDto.setLibraryName(library.getLibraryname());
        libraryDto.setLibraryCity(library.getLibrarycity());
        libraryDto.setLat(library.getLat());
        libraryDto.setLng(library.getLng());
        System.out.println(libraryDto);
        return libraryDto;
    }

    public Library mapToEntity(LibraryDto libraryDto) {
        Library library = new Library();
        library.setLibraryaddress(libraryDto.getLibraryAddress());
        library.setLibraryname(libraryDto.getLibraryName());
        library.setLibrarycity(libraryDto.getLibraryCity());
        library.setLat(libraryDto.getLat());
        library.setLng(libraryDto.getLng());
        return library;
    }

//    public OrderItem createOrder(OrderItem orderItem){
//return orderRepo.save(orderItem);
//    }
}