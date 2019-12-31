package com.example.author.service;

import com.example.author.model.Author;
import com.example.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class AuthorImp implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Override
    public ResponseEntity addNewAuthor(Author author) {
        return new ResponseEntity(authorRepository.save(author), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity getAuthorById(Long id) {
        Optional<Author>optionalAuthor=authorRepository.findById(id) ;
        if(!optionalAuthor.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(optionalAuthor.get(),HttpStatus.FOUND);
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public List getAuthorsByAddress(String address) {
        return authorRepository.findAuthorsByAddress(address);
    }


    @Override
    public List getAuthors() {
        return (List)authorRepository.findAll();
    }
}
