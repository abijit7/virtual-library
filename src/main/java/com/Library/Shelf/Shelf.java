package com.Library.Shelf;

import com.Library.Book.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String location;
    @OneToMany( mappedBy = "shelf", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> book;

}
