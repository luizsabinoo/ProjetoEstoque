package com.example.project_stock.domain.estoque;

import com.example.project_stock.domain.localizacao.Localizacao;
import com.example.project_stock.domain.produtos.Produtos;
import jakarta.validation.constraints.NotNull;

public record EstoqueDTO(@NotNull int quantidade, @NotNull Produtos produto, @NotNull Localizacao localizacao) {
}
