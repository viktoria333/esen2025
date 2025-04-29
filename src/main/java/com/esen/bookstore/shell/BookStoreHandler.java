package com.esen.bookstore.shell;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.service.BookStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Bookstore ralted command")
@RequiredArgsConstructor
public class BookStoreHandler {
    private final BookStoreService bookStoreService;

    @ShellMethod(value = "List all bookstore", key = "List bookstores")
    public String ListBookStores(){
        return bookStoreService.findAll()
                .stream()
                .map(bookstore -> {
                    bookstore.setInventory(null);
                    return bookstore;
                }).map(Bookstore::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(value = "Deletes a bookstore", key = "Delete bookstore")
    public void deleteBookStore(Long id){
         bookStoreService.delete(id);
    }
}
