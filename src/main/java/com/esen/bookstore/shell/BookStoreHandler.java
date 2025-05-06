package com.esen.bookstore.shell;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.service.BookStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Map;
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

    @ShellMethod(value = "Creates a bookstore", key = "create bookstore")
    public void createBookStore(Long id, String location, Double priceModifer, Double moneyInCashRegister) {
        bookStoreService.save(Bookstore.builder()
                .id(id)
                .location(location)
                .priceModifier(priceModifer)
                .moneyInCashRegister(moneyInCashRegister)
                .build());
    }

   @ShellMethod(value = "Updates a bookstore", key = "update bookstore")
    public String updateBook(
           @ShellOption(defaultValue = ShellOption.NULL) Long id,
           @ShellOption(defaultValue = ShellOption.NULL) String location,
           @ShellOption(defaultValue = ShellOption.NULL) Double priceModifier,
           @ShellOption(defaultValue = ShellOption.NULL)  Double moneyInCashRegister
   ) {
       return bookStoreService.update(id,location, priceModifier, moneyInCashRegister).toString();
   }







}
