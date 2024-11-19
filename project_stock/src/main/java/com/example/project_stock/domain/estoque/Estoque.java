package com.example.project_stock.domain.estoque;

import com.example.project_stock.domain.fornecedores.Fornecedores;
import com.example.project_stock.domain.produtos.Produtos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Entity(name = "estoque")
@Table(name = "estoque")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "Id")


public class Estoque extends RepresentationModel<Estoque> {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String Id;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "produtos_id")
    private Produtos produtos;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Fornecedores fornecedores;


}
