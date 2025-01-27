package com.alex.library.bookmanagementsystem.controllers;


import com.alex.library.bookmanagementsystem.model.dto.LibraryDto;
import com.alex.library.bookmanagementsystem.service.LibraryService;
import com.alex.library.bookmanagementsystem.service.UserService;
import com.alex.library.bookmanagementsystem.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryController {

    private final LibraryService libraryService;

    private final UserService userService;


    public LibraryController(LibraryService libraryService, UserService userService, BookService bookService) {
        this.libraryService = libraryService;
        this.userService = userService;
    }

    // Show insert pharmacy form
    @GetMapping("/insertlib")
    public String librarypage(ModelMap modelMap) {
        modelMap.addAttribute("libraryDto", new LibraryDto());
        return "libraryadd.html";
    }

    // Insert the pharmacy to db
    @PostMapping("/doinsertlib")
    public String insertLibrary(@ModelAttribute("libraryDto") LibraryDto libraryDto) {
        System.out.println(libraryDto);
        //TODO implement this. Only for admins
        libraryService.create(libraryDto);
        return "redirect:/alllibraries";
    }

    @GetMapping("/alllibraries")
    public String alllibraries(ModelMap modelMap) {
        modelMap.addAttribute("libraryDtoList", libraryService.getAllLibraries());

        return "alllibrarytable";
    }


}
