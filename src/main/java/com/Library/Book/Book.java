package com.Library.Book;

import com.Library.Shelf.Shelf;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Book_api")
public class Book {
    @Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String bookName;
    private String category;
    private String language;
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    @JsonBackReference
    private Shelf shelf;

    public Book(Integer id, String bookName, String category, String language) {
        this.id = id;
        this.bookName = bookName;
        this.category = category;
        this.language = language;
    }
}
