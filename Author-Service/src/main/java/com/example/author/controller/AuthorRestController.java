package com.example.author.controller;

import com.example.author.model.Author;
import com.example.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorRestController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/getauthors",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getauthors(){
        List<Author>authors=authorService.getAuthors();
        if(authors.size()==0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(authors,HttpStatus.OK);
    }
    @RequestMapping(value = "/AddNewAuthor",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNewAuthor(@RequestBody Author author){
        return authorService.addNewAuthor(author);
    }
    @RequestMapping(value = "/getAuthorById/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAuthorById(@PathVariable("id")Long id){
        return authorService.getAuthorById(id);
    }
    @RequestMapping(value = "/getAuthorByName/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAuthorByName(@PathVariable("name")String name){
       Optional<Author>optionalAuthor=authorService.getAuthorByName(name);
       if(!optionalAuthor.isPresent()){
           return new ResponseEntity(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity(optionalAuthor.get(),HttpStatus.FOUND);
    }



}
