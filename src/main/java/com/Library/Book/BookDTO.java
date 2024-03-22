package com.Library.Book;

import com.Library.Shelf.Shelf;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO{
        Integer id;
        @NotEmpty(message = "book name is compulsory")
        String bookName;
        String category;
        String language;
        Integer shelfId;

}
