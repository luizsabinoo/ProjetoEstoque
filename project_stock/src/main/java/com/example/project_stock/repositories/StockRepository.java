package com.example.project_stock.repositories;

import com.example.project_stock.domain.estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Estoque, String> {
}
