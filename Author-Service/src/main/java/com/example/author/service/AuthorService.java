package com.example.author.service;

import com.example.author.model.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AuthorService {
    public ResponseEntity addNewAuthor(Author author);
    public ResponseEntity getAuthorById(Long id);
    public  Optional<Author>getAuthorByName(String name);
    public List getAuthorsByAddress(String address);
    public List getAuthors();
}
