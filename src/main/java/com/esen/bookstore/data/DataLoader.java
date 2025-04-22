package com.esen.bookstore.data;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.model.Bookstore;
import com.esen.bookstore.reopsitory.BookRepository;
import com.esen.bookstore.reopsitory.BookStoreRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader {

    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    @Value("classpath:data/books.json")
    private Resource booksResource;

    @Value("classpath:data/bookstores.json")
    private Resource bookstoreResource;

    @PostConstruct //letrehozas utan futassa le metodust
    public void loadData() {
        var objectMapper = new ObjectMapper();

        try {
            var booksJson = StreamUtils.copyToString(booksResource.getInputStream(), StandardCharsets.UTF_8);
            var books = objectMapper.readValue(booksJson, new TypeReference<List<Book>>() {});
            bookRepository.saveAll(books);

            var bookstoreJson = StreamUtils.copyToString(bookstoreResource.getInputStream(), StandardCharsets.UTF_8);
            var bookstores = objectMapper.readValue(bookstoreJson, new TypeReference<List<Bookstore>>() {
            });

            bookstores.forEach(bookstore -> bookstore.setInventory(books.stream()
                        .collect(Collectors.toMap(book -> book,
                                book -> ThreadLocalRandom.current().nextInt(1,50))
                        )
            ));

            bookStoreRepository.saveAll(bookstores);

            log.info("successfully loadaed entities to the database");
        } catch (IOException e) {
            System.out.println("Cannot seed database" + e);
        }

    }

}
