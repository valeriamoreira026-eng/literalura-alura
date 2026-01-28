package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Busca livros pelo título
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Busca livros por idioma
    List<Livro> findByIdioma(String idioma);

    // Busca os 10 livros mais baixados (para feature extra)
    @Query("SELECT l FROM Livro l ORDER BY l.numeroDownloads DESC LIMIT 10")
    List<Livro> findTop10ByDownloads();

    // Busca livros por autor
    List<Livro> findByAutorNomeContainingIgnoreCase(String nomeAutor);
}