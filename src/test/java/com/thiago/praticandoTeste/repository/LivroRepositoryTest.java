package com.thiago.praticandoTeste.repository;

import com.thiago.praticandoTeste.model.Autor;
import com.thiago.praticandoTeste.model.Livro;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Test
    @DisplayName("should created a book")
    public void TestCreateLivro(){
        Livro livro = Livro.builder()
                .titulo("Java 8")
                .data_publicacao(LocalDate.now())
                .autor(Autor.builder().nome("Josehp Lomdrow").idade(87).build())
                .build();

        Livro livroPersistido = this.livroRepository.save(livro);

        Assertions.assertThat(livroPersistido.getId()).isNotNull();
        Assertions.assertThat(livroPersistido.getAutor().getId()).isNotNull();
    }

    @Test
    @DisplayName("should return a List book especific size")
    public void shouldReturnSizeListLivrosEqualsTwo(){
        Livro primeiroLIvro = Livro.builder()
                .titulo("Java 8")
                .data_publicacao(LocalDate.now())
                .autor(Autor.builder().nome("JOsehp Lomdrow").idade(87).build())
                .build();

        Livro segundoLivro = Livro.builder()
                .titulo("Logica de programação")
                .data_publicacao(LocalDate.of(1999, 8, 20))
                .autor(Autor.builder().nome("Crystian Buller").idade(86).build())
                .build();

        this.livroRepository.saveAll(Lists.newArrayList(primeiroLIvro, segundoLivro));

        List<Livro> livros = this.livroRepository.findAll();
        Assertions.assertThat(livros.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("should return a autor persistent with book")
    public void shouldReturnAutorWithIdIsNotNull() {
        Livro livroNew = Livro.builder()
                .titulo("Logica de programação")
                .data_publicacao(LocalDate.of(1999, 8, 20))
                .autor(Autor.builder().nome("Crystian Buller").idade(86).build())
                .build();

        this.livroRepository.save(livroNew);

        List<Livro> livros = this.livroRepository.findAll();
        Assertions.assertThat(livros.get(0).getAutor().getId()).isNotNull();
    }

    @Test
    @DisplayName("should delete a book")
    public void shouldDeleteOneBook() {
        Livro livroNew = Livro.builder()
                .titulo("Logica de programação")
                .data_publicacao(LocalDate.of(1999, 8, 20))
                .autor(Autor.builder().nome("Crystian Buller").idade(86).build())
                .build();

        this.livroRepository.save(livroNew);

        Livro livro = this.livroRepository.findByTitulo("Logica de programação");
        this.livroRepository.delete(livro);

        List<Livro> livros = this.livroRepository.findAll();
        Assertions.assertThat(livros.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("should update a book")
    public void shouldUpdateOneBook() {
        Livro segundoLivro = Livro.builder()
                .titulo("Logica de programação")
                .data_publicacao(LocalDate.of(1999, 8, 20))
                .autor(Autor.builder().nome("Crystian Buller").idade(86).build())
                .build();

        this.livroRepository.save(segundoLivro);

        Livro livro = this.livroRepository.findByTitulo("Logica de programação");
        livro.setTitulo("Orientação a objeto 2");

        Long idLivro = livro.getId();

        this.livroRepository.save(livro);

        Assertions.assertThat(livro.getId()).isEqualTo(idLivro);
        Assertions.assertThat(livro.getTitulo()).isEqualTo("Orientação a objeto 2");
    }
}
