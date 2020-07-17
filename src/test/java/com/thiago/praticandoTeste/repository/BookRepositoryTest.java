package com.thiago.praticandoTeste.repository;

import com.thiago.praticandoTeste.model.Author;
import com.thiago.praticandoTeste.model.Book;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("should created a book")
    public void TestCreateLivro(){
        Book book = Book.builder()
                .title("Java 8")
                .date_publication(LocalDate.now())
                .author(Author.builder().name("Josehp Lomdrow").age(87).build())
                .build();

        Book bookPersistido = this.bookRepository.save(book);

        Assertions.assertThat(bookPersistido.getId()).isNotNull();
        Assertions.assertThat(bookPersistido.getAuthor().getId()).isNotNull();
    }

    @Test
    @DisplayName("should return a List book especific size")
    public void shouldReturnSizeListLivrosEqualsTwo(){
        Book primeiroLIvro = Book.builder()
                .title("Java 8")
                .date_publication(LocalDate.now())
                .author(Author.builder().name("JOsehp Lomdrow").age(87).build())
                .build();

        Book segundoBook = Book.builder()
                .title("Logica de programação")
                .date_publication(LocalDate.of(1999, 8, 20))
                .author(Author.builder().name("Crystian Buller").age(86).build())
                .build();

        this.bookRepository.saveAll(Lists.newArrayList(primeiroLIvro, segundoBook));

        List<Book> books = this.bookRepository.findAll();
        Assertions.assertThat(books.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should return a autor persistent with book")
    public void shouldReturnAutorWithIdIsNotNull() {
        Book bookNew = Book.builder()
                .title("Logica de programação")
                .date_publication(LocalDate.of(1999, 8, 20))
                .author(Author.builder().name("Crystian Buller").age(86).build())
                .build();

        this.bookRepository.save(bookNew);

        List<Book> books = this.bookRepository.findAll();
        Assertions.assertThat(books.get(0).getAuthor().getId()).isNotNull();
    }

    @Test
    @DisplayName("should delete a book")
    public void shouldDeleteOneBook() {
        Book bookNew = Book.builder()
                .title("Logica de programação")
                .date_publication(LocalDate.of(1999, 8, 20))
                .author(Author.builder().name("Crystian Buller").age(86).build())
                .build();

        this.bookRepository.save(bookNew);

        Book book = this.bookRepository.findByTitle("Logica de programação");
        this.bookRepository.delete(book);

        List<Book> books = this.bookRepository.findAll();
        Assertions.assertThat(books.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("should update a book")
    public void shouldUpdateOneBook() {
        Book segundoBook = Book.builder()
                .title("Logica de programação")
                .date_publication(LocalDate.of(1999, 8, 20))
                .author(Author.builder().name("Crystian Buller").age(86).build())
                .build();

        this.bookRepository.save(segundoBook);

        Book book = this.bookRepository.findByTitle("Logica de programação");
        book.setTitle("Orientação a objeto 2");

        Long idLivro = book.getId();

        this.bookRepository.save(book);

        Assertions.assertThat(book.getId()).isEqualTo(idLivro);
        Assertions.assertThat(book.getTitle()).isEqualTo("Orientação a objeto 2");
    }
}
