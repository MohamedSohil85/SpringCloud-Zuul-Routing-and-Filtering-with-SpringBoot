package com.example.bibliothek.bibliothek.repository;

import com.example.bibliothek.bibliothek.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Long> {

    public Optional<Book>findBookByName(String name);

    public List<Book>findBooksByGenre(String genre);
}
