package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Busca autores pelo nome
    List<Autor> findByNomeContainingIgnoreCase(String nome);

    // Busca autores vivos em um determinado ano
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento >= :ano OR a.anoFalecimento IS NULL)")
    List<Autor> findAutoresVivosEm(Integer ano);

    // Busca autores por ano de nascimento
    List<Autor> findByAnoNascimento(Integer ano);
}