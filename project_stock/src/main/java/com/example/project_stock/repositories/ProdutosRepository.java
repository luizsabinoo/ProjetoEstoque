package com.example.project_stock.repositories;

import com.example.project_stock.domain.produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, String> {
}
