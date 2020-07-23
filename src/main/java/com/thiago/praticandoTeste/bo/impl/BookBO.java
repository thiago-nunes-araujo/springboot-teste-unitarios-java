package com.thiago.praticandoTeste.bo.impl;

import com.thiago.praticandoTeste.bo.interfaces.IBookBO;
import com.thiago.praticandoTeste.model.Book;
import com.thiago.praticandoTeste.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookBO implements IBookBO {

    private BookRepository repository;

    private BookBO(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<List<Book>> findAll() {
        return Optional.ofNullable(this.repository.findAll());
    }

    @Override
    public Optional<Book> findBookById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public Book findBookByAuthorName(String authorName) {
        return repository.findBookByAuthorName(authorName);
    }
}
