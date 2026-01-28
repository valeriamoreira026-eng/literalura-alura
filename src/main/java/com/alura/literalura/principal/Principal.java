package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LivroService livroService;

    private Scanner scanner = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== LITERALURA - CATÁLOGO DE LIVROS ===\n");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("6 - Top 10 livros mais baixados");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer

                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosEmAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        listarTop10Downloads();
                        break;
                    case 0:
                        System.out.println("\nSaindo... Até logo! 📚");
                        break;
                    default:
                        System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("\nErro: Digite um número válido!");
                scanner.nextLine(); // Limpar entrada inválida
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.println("\n=== BUSCAR LIVRO PELO TÍTULO ===\n");
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        livroService.buscarLivroPorTitulo(titulo);
    }

    private void listarLivrosRegistrados() {
        System.out.println("\n=== LIVROS REGISTRADOS ===\n");

        var livros = livroService.listarTodosLivros();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            for (Livro livro : livros) {
                System.out.println("📖 " + livro.getTitulo() +
                        " | Autor: " + (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido") +
                        " | Idioma: " + livro.getIdioma() +
                        " | Downloads: " + livro.getNumeroDownloads());
            }
            System.out.println("\nTotal: " + livros.size() + " livro(s)");
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("\n=== AUTORES REGISTRADOS ===\n");

        var autores = livroService.listarTodosAutores();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            for (Autor autor : autores) {
                System.out.println("👤 " + autor.getNome() +
                        " | Nascimento: " + (autor.getAnoNascimento() != null ? autor.getAnoNascimento() : "?") +
                        " | Falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "?"));
            }
            System.out.println("\nTotal: " + autores.size() + " autor(es)");
        }
    }

    private void listarAutoresVivosEmAno() {
        System.out.println("\n=== AUTORES VIVOS EM UM ANO ===\n");
        System.out.print("Digite o ano: ");

        try {
            Integer ano = scanner.nextInt();
            scanner.nextLine();

            var autores = livroService.listarAutoresVivosEm(ano);

            if (autores.isEmpty()) {
                System.out.println("\nNenhum autor vivo em " + ano);
            } else {
                System.out.println("\nAutores vivos em " + ano + ":");
                for (Autor autor : autores) {
                    System.out.println("👤 " + autor.getNome() +
                            " (" + autor.getAnoNascimento() + " - " +
                            (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "presente") + ")");
                }
                System.out.println("Total: " + autores.size() + " autor(es)");
            }
        } catch (Exception e) {
            System.out.println("\nAno inválido!");
            scanner.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("\n=== LIVROS POR IDIOMA ===\n");
        System.out.println("Idiomas disponíveis: pt (português), en (inglês), es (espanhol), fr (francês)");
        System.out.print("Digite o código do idioma: ");

        String idioma = scanner.nextLine().toLowerCase();

        var livros = livroService.listarLivrosPorIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro encontrado no idioma: " + idioma);
        } else {
            System.out.println("\nLivros em " + idioma + ":");
            for (Livro livro : livros) {
                System.out.println("📖 " + livro.getTitulo() +
                        " | Autor: " + (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido") +
                        " | Downloads: " + livro.getNumeroDownloads());
            }
            System.out.println("\nTotal: " + livros.size() + " livro(s)");
        }
    }

    private void listarTop10Downloads() {
        System.out.println("\n=== TOP 10 LIVROS MAIS BAIXADOS ===\n");

        var livros = livroService.listarTop10Downloads();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            int posicao = 1;
            for (Livro livro : livros) {
                System.out.println(posicao + "º 📖 " + livro.getTitulo() +
                        " | Autor: " + (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido") +
                        " | Downloads: " + livro.getNumeroDownloads());
                posicao++;
            }
        }
    }
}