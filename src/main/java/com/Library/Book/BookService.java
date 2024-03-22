package com.Library.Book;

import com.Library.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookService {
    private final BookRepo libraryRepo;
    @Autowired
private final BookMapper bookMapper;

    public BookService(BookRepo libraryRepo, BookMapper bookMapper) {

        this.libraryRepo = libraryRepo;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBook() {
        return this.libraryRepo.findAll().stream()
                .map(bookMapper::convertBookToBookDTO).collect(Collectors.toList());
    }

    public BookDTO addBook(BookDTO bookDTO) {
       Book book = this.bookMapper.convertBookDTOToBook(bookDTO);
      Book savedBook = this.libraryRepo.save(book);
       return this.bookMapper.convertBookToBookDTO(savedBook);
    }

    public BookDTO findBookById(Integer id) {
   Book book = this.libraryRepo.findById(id).orElseThrow(()-> new NotFoundException("user not found of Id : "+id));
          if(book != null){
              return bookMapper.convertBookToBookDTO(book);
          }
          return null;
    }

    public BookDTO findBookByName(String bookName) {
   Book book = this.libraryRepo.findByBookName(bookName);
   if(book !=null){
       return bookMapper.convertBookToBookDTO(book);
   }else {
       throw new NotFoundException("Book not found of Name :"+" "+bookName);
   }

    }


    public void deleteBookById(Integer id) {
        Optional<Book> book = Optional.ofNullable(this.libraryRepo.findById(id).orElseThrow(()->
                new NotFoundException("Book not found of Id :"+" "+id)));
        this.libraryRepo.deleteById(id);
    }


    public BookDTO updateBookById(BookDTO bookDTO, Integer id) {
        Book book =  this.libraryRepo.findById(id).orElseThrow(()->
                new NotFoundException("Book not found of Id :"+" "+id));
        book.setBookName(bookDTO.getBookName());
        book.setLanguage(bookDTO.getLanguage());
        book.setCategory(bookDTO.getCategory());

        Book savedBook = this.libraryRepo.save(book);
        return this.bookMapper.convertBookToBookDTO(savedBook);
    }
}
