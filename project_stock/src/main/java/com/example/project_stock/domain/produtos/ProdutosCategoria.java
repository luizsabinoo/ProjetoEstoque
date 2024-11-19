package com.example.project_stock.domain.produtos;

public enum ProdutosCategoria {

    ELETRODOMESTICO("eletrodomestico"),
    CULINARIA("culinaria");

    private String categoria;
    ProdutosCategoria(String categoria) {
        this.categoria = categoria;

    }

    public String getCategoria() {
        return categoria;
    }
}
