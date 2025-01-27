package com.alex.library.bookmanagementsystem.controllers;

import com.alex.library.bookmanagementsystem.model.Book;
import com.alex.library.bookmanagementsystem.service.CategoryService;
import com.alex.library.bookmanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/insertbook")
    public String showBookForm(ModelMap modelMap) {
        Book book = new Book();

        modelMap.addAttribute("book", book);
        // put all categories to the model. I need them to create the dropdown list
        modelMap.addAttribute("categories", categoryService.getAllCategories());
        return "bookform";
    }

    @PostMapping("/doinsertbook")
    public String doInsertBook(@Valid @ModelAttribute Book book, BindingResult result,
                                   ModelMap modelMap,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "bookform";
        }
        bookService.insertBook(book);
        redirectAttributes.addFlashAttribute("infomessage", "Book: " + book.getBookname() + "" +
                "inserted successfully");

        return "redirect:/showdashboard";
    }

    @GetMapping("/allbooks")
    public String showAllBooks(ModelMap modelMap) {
        List<Book> allNotDisabledBooks = bookService.getAllNotDisabledBooks();
        modelMap.addAttribute("books", allNotDisabledBooks);
        return "allbookstable";
    }

    @GetMapping("/bookedit/{bookid}")
    public String updateBook(@PathVariable(name = "bookid") final Integer bookid,
                             ModelMap modelMap) {
        modelMap.addAttribute("book", bookService.getBookById(bookid));
        modelMap.addAttribute("categories", categoryService.getAllCategories());
        return "bookedit";
    }

    @PostMapping("/doupdatebook")
    public String doUpdateBook(@Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "bookedit";
        }
        bookService.updateBook(book);
        return "redirect:/allbooks";
    }

    @PostMapping("/bookdelete/{bookid}")
    public String deleteBook(@PathVariable(name = "bookid") final Integer bookid,
                             ModelMap modelMap) {
        bookService.disableBookById(bookid);
        return "redirect:/allbooks";
    }

}