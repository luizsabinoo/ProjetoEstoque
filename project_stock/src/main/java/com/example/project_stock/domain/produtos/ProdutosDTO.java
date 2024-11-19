package com.example.project_stock.domain.produtos;

import jakarta.validation.constraints.NotNull;

public record ProdutosDTO(
        @NotNull String name,
        @NotNull String categoria,
        @NotNull String unidade_medida
) {
}
