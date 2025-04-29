package com.esen.bookstore.service;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.reopsitory.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    public List<Bookstore> findAll(){
        return bookStoreRepository.findAll();
    }

    public void delete(Long id) {
        if (!bookStoreRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot find with id" + id);
        }

        var book = bookStoreRepository.findById(id).get();
        bookStoreRepository.delete(book);

    }

    public void removeBookFromInventory(Book book) {
        bookStoreRepository.findAll()
                .forEach(bookstore -> {
                    bookstore.getInventory()
                            .remove(book);
                    bookStoreRepository.save(bookstore);
                });
        }
    }