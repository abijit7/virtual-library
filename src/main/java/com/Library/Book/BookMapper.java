package com.Library.Book;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    @Autowired
    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDTO convertBookToBookDTO(Book book){
modelMapper.getConfiguration().setMatchingStrategy(
        MatchingStrategies.LOOSE
);
        BookDTO bookDTO = new BookDTO();
        bookDTO = modelMapper.map(book,BookDTO.class);
        return bookDTO;
    }
    public Book convertBookDTOToBook(BookDTO bookDTO){
        modelMapper.getConfiguration().setMatchingStrategy(
                MatchingStrategies.LOOSE
        );
        Book book = new Book();
        book = this.modelMapper.map(bookDTO,Book.class);
        return book;


    }
}
