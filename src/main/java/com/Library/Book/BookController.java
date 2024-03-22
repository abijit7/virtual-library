package com.Library.Book;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBook(){
        return this.bookService.getAllBook();
    }
    @PostMapping("/add")
    public BookDTO addBook( @Valid @RequestBody BookDTO bookDTO){
        return this.bookService.addBook(bookDTO);
    }
    @GetMapping("id/{id}")
    public BookDTO findByBookId(@PathVariable("id") Integer id){
        return this.bookService.findBookById(id);
    }
    @GetMapping("name/{book-name}")
    public BookDTO findBookByName(@PathVariable("book-name")String
                                              bookName){
return this.bookService.findBookByName(bookName);
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteBookById(@PathVariable("id")Integer id){
         this.bookService.deleteBookById(id);
    }
    @PutMapping("/update/id/{id}")
    public BookDTO updateBookById(@RequestBody BookDTO bookDTO,
                                  @PathVariable("id") Integer id){
        return this.bookService.updateBookById(bookDTO,id);
    }


}
