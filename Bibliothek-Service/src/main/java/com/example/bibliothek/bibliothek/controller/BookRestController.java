package com.example.bibliothek.bibliothek.controller;

import com.example.bibliothek.bibliothek.model.Book;
import com.example.bibliothek.bibliothek.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookRestController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/getBooks",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List<Book>getBooks(){
        List<Book>books=(List)bookRepository.findAll();
        return books;
    }
    @RequestMapping(value = "/addNewBook",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ResponseEntity addNewBook(@RequestBody Book book){
      List<Book>bookList=(List)bookRepository.findAll();
     for(Book temp:bookList){
         if(temp.getName().equals(book.getName())){
             return new ResponseEntity(HttpStatus.FOUND);
         }
     }
     return new ResponseEntity(bookRepository.save(book),HttpStatus.CREATED);
    }
    @RequestMapping(value = "/findBookByAuthor/{name}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List findBooksByAuthor(@PathVariable("name")String name){
        List<Book>books=(List)bookRepository.findAll();
      Comparator<Book>bookComparator=Comparator.comparing(Book::getName).thenComparing(Book::getAuthor);
      return books
              .stream()
              .filter(b->b.getAuthor().equalsIgnoreCase(name))
              .sorted(bookComparator)
              .collect(Collectors.toList());

    }
    @RequestMapping(value = "/findBookByName/{name}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ResponseEntity findBookByName(@PathVariable("name")String name){
        Optional<Book>optionalBook=bookRepository.findBookByName(name);
       if(!optionalBook.isPresent()){
           return new ResponseEntity(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity(optionalBook.get(),HttpStatus.FOUND);
    }
    @RequestMapping(value = "/findBorrower/{name}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List findBorrower(@PathVariable("name")String name){

        List<Book>books=(List)bookRepository.findAll();
        return books.stream().sorted().filter(book -> book.getUserName().startsWith(name)).collect(Collectors.toList());
    }

}
