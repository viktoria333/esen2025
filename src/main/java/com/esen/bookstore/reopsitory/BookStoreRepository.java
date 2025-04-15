package com.esen.bookstore.reopsitory;

import com.esen.bookstore.model.Bookstore;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface BookStoreRepository extends JpaRepository<Bookstore, Long> {

}
