package com.thiago.praticandoTeste.bo.interfaces;

import com.thiago.praticandoTeste.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookBO {

    Optional<List<Book>> findAll();

    Optional<Book> findBookById(long id);

    Book findBookByAuthorName(String seller_melo);
}
