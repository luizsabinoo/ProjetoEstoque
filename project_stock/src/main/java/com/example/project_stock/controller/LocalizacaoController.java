package com.example.project_stock.controller;

import com.example.project_stock.domain.localizacao.Localizacao;
import com.example.project_stock.domain.localizacao.LocalizacaoDTO;
import com.example.project_stock.repositories.LocalizacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/localizacao")

public class LocalizacaoController {
    @Autowired
    LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public ResponseEntity<List<Localizacao>> findAll() {
        List<Localizacao> localizacoes = localizacaoRepository.findAll();
        if (localizacoes.isEmpty()) {
            localizacoes.forEach(localizacao -> {
                String id = localizacao.getId();
                localizacao.add(linkTo(methodOn(LocalizacaoController.class).getOne(id)).withSelfRel());
            });
        }
        return ResponseEntity.status(HttpStatus.OK).body(localizacoes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String id) {
        Optional<Localizacao> localizacao = localizacaoRepository.findById(id);
        if (localizacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nennhuma Localização");
        }
        localizacao.get().add(linkTo(methodOn(LocalizacaoController.class).getOne(id)).withRel("Lista de localizacoes"));
        return ResponseEntity.status(HttpStatus.OK).body(localizacao.get());

    }
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid LocalizacaoDTO localizacaoDto) {
        Localizacao localizacao = new Localizacao();
        BeanUtils.copyProperties(localizacaoDto, localizacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(localizacaoRepository.save(localizacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Optional<Localizacao> localizacao = localizacaoRepository.findById(id);
        if (localizacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma localização");
        }
        return ResponseEntity.status(HttpStatus.OK).body(localizacao.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id ,@RequestBody @Valid LocalizacaoDTO localizacaoDto) {
        Optional<Localizacao> localizacao = localizacaoRepository.findById(id);
        if (localizacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma Localizacao");
        }
        Localizacao localizacaoUpdate = localizacao.get();
        BeanUtils.copyProperties(localizacaoDto, localizacaoUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(localizacaoRepository.save(localizacaoUpdate));
    }

}
