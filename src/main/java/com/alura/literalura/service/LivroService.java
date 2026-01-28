package com.alura.literalura.service;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.DadosResultado;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConverteDados conversor;

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public void buscarLivroPorTitulo(String titulo) {
        try {
            System.out.println("\n🔍 Buscando livro na API Gutendex...");

            // 1. Buscar na API
            String url = URL_BASE + titulo.replace(" ", "%20");
            System.out.println("📡 URL: " + url);

            String json = consumoAPI.obterDados(url);

            // 2. Converter JSON para objeto
            DadosResultado dadosResultado = conversor.obterDados(json, DadosResultado.class);

            // 3. Verificar se encontrou livros
            if (dadosResultado == null || dadosResultado.livros() == null || dadosResultado.livros().isEmpty()) {
                System.out.println("\n❌ Nenhum livro encontrado com o título: " + titulo);
                return;
            }

            // 4. Pegar o primeiro livro
            DadosLivro dadosLivro = dadosResultado.livros().get(0);
            System.out.println("✅ Livro encontrado: " + dadosLivro.titulo());

            // 5. Verificar se já existe no banco
            Optional<Livro> livroExistente = livroRepository.findByTituloContainingIgnoreCase(dadosLivro.titulo())
                    .stream()
                    .findFirst();

            if (livroExistente.isPresent()) {
                System.out.println("\n⚠️  Este livro já está cadastrado!");
                System.out.println("📖 " + livroExistente.get().getTitulo());
                return;
            }

            // 6. Salvar autor (se não existir)
            Autor autor = salvarAutor(dadosLivro);

            // 7. Salvar livro
            Livro livro = new Livro();
            livro.setTitulo(dadosLivro.titulo());

            // Pega o primeiro idioma (ou "desconhecido" se não tiver)
            String idioma = "desconhecido";
            if (dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty()) {
                idioma = dadosLivro.idiomas().get(0);
            }
            livro.setIdioma(idioma);

            livro.setNumeroDownloads(dadosLivro.numeroDownloads() != null ? dadosLivro.numeroDownloads() : 0);
            livro.setAutor(autor);

            livroRepository.save(livro);

            // 8. Mostrar resultado
            System.out.println("\n✅ LIVRO CADASTRADO COM SUCESSO!");
            System.out.println("📖 Título: " + livro.getTitulo());
            System.out.println("👤 Autor: " + autor.getNome());
            System.out.println("🌐 Idioma: " + livro.getIdioma());
            System.out.println("⬇️  Downloads: " + livro.getNumeroDownloads());

        } catch (Exception e) {
            System.out.println("\n❌ Erro ao buscar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Autor salvarAutor(DadosLivro dadosLivro) {
        // Se não tem autores, retorna autor desconhecido
        if (dadosLivro.autores() == null || dadosLivro.autores().isEmpty()) {
            Autor autor = new Autor("Autor Desconhecido", null, null);
            return autorRepository.save(autor);
        }

        // Pega o primeiro autor
        DadosAutor dadosAutor = dadosLivro.autores().get(0);

        // Verifica se autor já existe
        List<Autor> autoresExistentes = autorRepository.findByNomeContainingIgnoreCase(dadosAutor.nome());
        if (!autoresExistentes.isEmpty()) {
            return autoresExistentes.get(0);
        }

        // Cria novo autor
        Autor autor = new Autor();
        autor.setNome(dadosAutor.nome());
        autor.setAnoNascimento(dadosAutor.anoNascimento());
        autor.setAnoFalecimento(dadosAutor.anoFalecimento());

        return autorRepository.save(autor);
    }

    // ====== MÉTODOS PARA O MENU ======

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEm(Integer ano) {
        return autorRepository.findAutoresVivosEm(ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    public List<Livro> listarTop10Downloads() {
        return livroRepository.findTop10ByDownloads();
    }
}