package com.Library.Book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

class BookRepoTest {
    @Autowired
private BookRepo bookRepo;
Book book;
    @BeforeEach
    void setUp() {
 book = new Book(12,"ramayan","history","nepali");
bookRepo.save(book);
    }

    @AfterEach
    void tearDown() {
book = null;
bookRepo.deleteAll();;
    }

    @Test
    void findById() {

        //given

        //execution
Optional<Book> book1 = bookRepo.findById(12);
assertEquals(book1.get(),book.getId());
        //output
    }
}