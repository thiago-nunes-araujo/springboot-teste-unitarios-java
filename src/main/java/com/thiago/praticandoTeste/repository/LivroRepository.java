package com.thiago.praticandoTeste.repository;

import com.thiago.praticandoTeste.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Livro findByTitulo(String titulo);
}
