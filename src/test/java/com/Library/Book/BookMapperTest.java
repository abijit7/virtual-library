package com.Library.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    @Autowired
private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper(new ModelMapper());
    }

    @Test
    void convertBookDTOToBook() {
BookDTO bookDTO = new BookDTO(12,"society","history","nepali",32);
Book book = bookMapper.convertBookDTOToBook(bookDTO);
assertEquals(bookDTO.getBookName(),book.getBookName());
assertEquals(bookDTO.getCategory(),book.getCategory());
assertEquals(bookDTO.getLanguage(),book.getLanguage());
assertNotNull(book.getShelf());
assertEquals(bookDTO.getShelfId(),book.getShelf().getId());

    }
    @Test
    void convertBookToBookDTO(){
        //given
        Book book = new Book(1,"marqee","bio","english");
        //when
        BookDTO bookDTO = bookMapper.convertBookToBookDTO(book);
        //then
        assertEquals(book.getBookName(),bookDTO.getBookName());
        assertEquals(book.getCategory(),bookDTO.getCategory());
        assertEquals(book.getLanguage(),bookDTO.getLanguage());

    }
}