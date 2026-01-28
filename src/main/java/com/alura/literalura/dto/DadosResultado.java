package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultado(
        @JsonAlias("count") Integer quantidade,
        @JsonAlias("results") List<DadosLivro> livros
) {
}