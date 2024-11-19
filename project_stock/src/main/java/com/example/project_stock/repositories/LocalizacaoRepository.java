package com.example.project_stock.repositories;

import com.example.project_stock.domain.localizacao.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {
}
