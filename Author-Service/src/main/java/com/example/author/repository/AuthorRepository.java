package com.example.author.repository;

import com.example.author.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {

public Optional<Author>findByName(String name);
public List<Author>findAuthorsByAddress(String address);


}
