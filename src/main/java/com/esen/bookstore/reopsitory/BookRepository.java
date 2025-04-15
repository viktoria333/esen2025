package com.esen.bookstore.reopsitory;

import com.esen.bookstore.model.Book;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface BookRepository extends JpaRepository<Book,Long> {
}
