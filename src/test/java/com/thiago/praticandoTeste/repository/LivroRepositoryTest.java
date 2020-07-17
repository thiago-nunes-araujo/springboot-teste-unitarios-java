package com.thiago.praticandoTeste.repository;

import com.thiago.praticandoTeste.model.Autor;
import com.thiago.praticandoTeste.model.Livro;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Before
    public void beforeTest(){
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

        List<Livro> livros = this.livroRepository.saveAll(Arrays.asList(primeiroLIvro, segundoLivro));
        livros.size();
    }

    @Test
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
    public void shouldReturnSizeListLivrosEqualsTwo(){
        List<Livro> livros = this.livroRepository.findAll();
        Assertions.assertThat(livros.size()).isEqualTo(2);
    }

    @Test
    public void shouldAutorWithIdIsNotNull() {
        List<Livro> livros = this.livroRepository.findAll();
        Assertions.assertThat(livros.get(0).getAutor().getId()).isNotNull();
        Assertions.assertThat(livros.get(1).getAutor().getId()).isNotNull();
    }

    @Test
    public void TestDeleteLivro() {
        Livro livro = this.livroRepository.findByTitulo("Logica de programação");
        this.livroRepository.delete(livro);

        List<Livro> livros = this.livroRepository.findAll();
        Assertions.assertThat(livros.size()).isEqualTo(1);
    }

    @Test
    public void TestPutLivro(){
        Livro livro = this.livroRepository.findByTitulo("Logica de programação");
        livro.setTitulo("Orientação a objeto 2");

        Long idLivro = livro.getId();

        this.livroRepository.save(livro);

        Assertions.assertThat(livro.getId()).isEqualTo(idLivro);
        Assertions.assertThat(livro.getTitulo()).isEqualTo("Orientação a objeto 2");
    }
}
