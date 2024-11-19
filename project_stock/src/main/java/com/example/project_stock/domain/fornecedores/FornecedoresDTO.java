package com.example.project_stock.domain.fornecedores;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedoresDTO(@NotBlank String nome,@NotBlank String contato) {
}
