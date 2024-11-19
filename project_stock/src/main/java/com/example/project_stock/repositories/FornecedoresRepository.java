package com.example.project_stock.repositories;

import com.example.project_stock.domain.fornecedores.Fornecedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedoresRepository extends JpaRepository<Fornecedores, String> {
}
