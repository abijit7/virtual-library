package com.Library.Book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private  BookRepo bookRepo;

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepo,bookMapper);
    }
    @Test
    public void should_save_a_student(){
BookDTO bookDTO = new BookDTO(1,"ramayan","history","nepali",2);
        Book book = new Book(1,"ramayan","history","nepali");
        //mock
        Mockito.when(bookMapper.convertBookDTOToBook(bookDTO)).thenReturn(book);
        Mockito.when(bookRepo.save(book)).thenReturn(book);
        Mockito.when(bookMapper.convertBookToBookDTO(book)).thenReturn(new BookDTO(1,
                "ramayan",
                "history",
                "nepali",2));
        //when
BookDTO saveDTO = bookService.addBook(bookDTO);
//then
        assertEquals(bookDTO.getBookName(),saveDTO.getBookName());
        assertEquals(bookDTO.getCategory(),saveDTO.getCategory());
        assertEquals(bookDTO.getLanguage(),saveDTO.getLanguage());
        verify(bookMapper,times(1)).convertBookDTOToBook(bookDTO);
        verify(bookRepo,times(1)).save(book);
        verify(bookMapper,times(1)).convertBookToBookDTO(book);
    }

    @Test
    void ShouldGetAllBook() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"ramayan","history","nepali"));
        when(bookRepo.findAll()).thenReturn(bookList);
        when(bookMapper.convertBookToBookDTO(any(Book.class))).thenReturn(new BookDTO(1,"ramayan","history","nepali",21));
List<BookDTO> getBook =  bookService.getAllBook();
assertEquals(bookList.size(),getBook.size());
verify(bookRepo,times(1)).findAll();
    }

    @Test
    void ShouldFindBookById() {
        Book book = new Book(1,"ramayan","history","nepali");
        //mock
        when(bookRepo.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookMapper.convertBookToBookDTO(any(Book.class))).thenReturn(new BookDTO(1,"ramayan","history","nepali",21));
    BookDTO bookDTO = bookService.findBookById(book.getId());
    assertEquals(book.getId(),bookDTO.getId());
    }

    @Test
    void ShouldFindBookByName() {
        Book book = new Book(1,"ramayan","history","nepali");
        //mock
        when(bookRepo.findByBookName(book.getBookName())).thenReturn(book);
        when(bookMapper.convertBookToBookDTO(book)).thenReturn(new BookDTO(1,"ramayan","history","nepali",32));
        BookDTO bookDTO = bookService.findBookByName(book.getBookName());
        assertEquals(book.getBookName(),bookDTO.getBookName());
    }

    @Test
    void ShouldDeleteBookById() {
        Book book = new Book(1,"ramayan","history","nepali");
        //mock
         doNothing().when(bookRepo).deleteById(book.getId());
         //act
         bookService.deleteBookById(book.getId());
         //assert
        verify(bookRepo).deleteById(book.getId());
    }
}