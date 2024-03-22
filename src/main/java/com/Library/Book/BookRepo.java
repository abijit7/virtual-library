package com.Library.Book;

import com.Library.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {
Book findByBookName(String s);
}
