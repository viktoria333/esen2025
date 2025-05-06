package com.esen.bookstore.service;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.reopsitory.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;


    public List<Bookstore> findAll(){
        return bookStoreRepository.findAll();
    }

    public void save(Bookstore bookstore) {
        bookStoreRepository.save(bookstore);
    }

    public Bookstore update(Long id, String location, Double priceModifier, Double moneyInCashRegister) {
        if (Stream.of(id, location, priceModifier, moneyInCashRegister).allMatch(Objects::isNull)) {
            throw new IllegalArgumentException("At least one input is require");
        }
        if (!bookStoreRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot find booksotre with id" + id);
        }

        var bookstore = bookStoreRepository.findById(id).get();

        if(location != null){
            bookstore.setLocation(location);
        }

        if(priceModifier != null){
            bookstore.setPriceModifier(priceModifier);
        }

        if(moneyInCashRegister != null){
            bookstore.setMoneyInCashRegister(moneyInCashRegister);
        }

        return bookStoreRepository.save(bookstore);
    }

    public void removeBookFromInventory(Book book) {
        bookStoreRepository.findAll()
                .forEach(bookstore -> {
                    bookstore.getInventory()
                            .remove(book);
                    bookStoreRepository.save(bookstore);
                });
        }

    public void delete(Long id) {
        if (!bookStoreRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot find with id" + id);
        }

        var book = bookStoreRepository.findById(id).get();
        bookStoreRepository.delete(book);

    }
}