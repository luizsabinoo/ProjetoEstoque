package com.example.project_stock.domain.localizacao;

import jakarta.validation.constraints.NotBlank;

public record LocalizacaoDTO(@NotBlank String localizacao) {
}
