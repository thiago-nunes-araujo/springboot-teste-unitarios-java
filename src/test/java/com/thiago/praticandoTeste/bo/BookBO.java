package com.thiago.praticandoTeste.bo;

import com.thiago.praticandoTeste.model.Author;
import com.thiago.praticandoTeste.model.Book;
import com.thiago.praticandoTeste.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@DataJpaTest
public class BookBO {

    @InjectMocks
    private com.thiago.praticandoTeste.bo.impl.BookBO bookBO;

    @Mock
    private BookRepository repository;

    private List<Book> books;

    @Test
    @DisplayName("should return list with size equals two")
    public void should_return_list_of_two_book() {
        when(repository.findAll()).thenReturn(books);

        Book primeiroLIvro = Book.builder()
                .id(1)
                .title("Java 8")
                .date_publication(LocalDate.now())
                .author(Author.builder().name("JOsehp Lomdrow").age(87).build())
                .build();

        Book segundoBook = Book.builder()
                .id(2)
                .title("Logica de programação")
                .date_publication(LocalDate.of(1999, 8, 20))
                .author(Author.builder().name("Crystian Buller").age(86).build())
                .build();

        this.books = new ArrayList<>();
        this.books.addAll(Arrays.asList(primeiroLIvro, segundoBook));

        List<Book> books = bookBO.findAll().get();
        Assertions.assertEquals(2, books.size());
    }

    @Test
    @DisplayName("Shoul return Null Book For id")
    public void shouldReturnNotFoundBook() {
        when(repository.findById(3L)).thenReturn(null);
        Assertions.assertNull(bookBO.findBookById(3L));
    }

    @Test
    @DisplayName("should return one book for Id")
    public void shouldReturnOneBookForId() {
        Optional<Book> book = Optional.ofNullable(Book.builder().id(2)
                .title("Logica de programação")
                .date_publication(LocalDate.of(1999, 8, 20))
                .author(Author.builder().name("Crystian Buller").age(86).build())
                .build());

        when(repository.findById(2L)).thenReturn(book);

        Assertions.assertNotNull(bookBO.findBookById(2L));
    }

    @Test
    @DisplayName("should return one book for filter Author Name")
    public void shouldReturnBookForAuthorName(){
        final String nameAuthor = "Seller Melo";

        Book book = Book.builder().id(2)
                .title("Livro de Coach")
                .date_publication(LocalDate.of(1875, 7, 15))
                .author(Author.builder().name("Seller Melo").age(78).build())
                .build();

        when(repository.findBookByAuthorName(nameAuthor)).thenReturn(book);

        Book bookCoach = bookBO.findBookByAuthorName("Seller Melo");

        Assertions.assertNotNull(book);
    }
}
