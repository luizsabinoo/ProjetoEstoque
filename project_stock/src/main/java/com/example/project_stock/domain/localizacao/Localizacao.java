package com.example.project_stock.domain.localizacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Entity(name = "localizacao")
@Table(name = "localizacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")



public class Localizacao extends RepresentationModel<Localizacao> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String localizacao;

}
