package com.thiago.praticandoTeste.repository;

import com.thiago.praticandoTeste.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String titulo);
}
