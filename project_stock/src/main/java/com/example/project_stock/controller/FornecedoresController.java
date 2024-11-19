package com.example.project_stock.controller;


import com.example.project_stock.domain.fornecedores.Fornecedores;
import com.example.project_stock.domain.fornecedores.FornecedoresDTO;
import com.example.project_stock.repositories.FornecedoresRepository;
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
@RequestMapping("/fornecedor")

public class FornecedoresController {
    @Autowired
    FornecedoresRepository repository;

    @GetMapping
    public ResponseEntity<List<Fornecedores>> getAll() {
        List<Fornecedores> fornecedores = repository.findAll();
        if (!fornecedores.isEmpty()) {
            fornecedores.forEach(fornecedor1 ->{
                String id = fornecedor1.getId();
                fornecedor1.add(linkTo(methodOn(FornecedoresController.class).getOne(id)).withSelfRel());
            });
        }
        return ResponseEntity.status(HttpStatus.OK).body(fornecedores);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String id) {
        Optional<Fornecedores> fornecedores = repository.findById(id);
        if(fornecedores.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não Existem Fornecedores");
        }
        fornecedores.get().add(linkTo(methodOn(FornecedoresController.class).getOne(id)).withRel("Lista de Fornecedores"));
        return ResponseEntity.status(HttpStatus.OK).body(fornecedores.get());
    }

    @PostMapping
    public ResponseEntity<Fornecedores> save(@RequestBody @Valid FornecedoresDTO fornecedoresDTO) {
        Fornecedores fornecedores = new Fornecedores();
        BeanUtils.copyProperties(fornecedoresDTO,fornecedores);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(fornecedores));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Optional<Fornecedores> fornecedores = repository.findById(id);
        if(fornecedores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem Fornecedores");

        }
        repository.delete(fornecedores.get());
        return ResponseEntity.status(HttpStatus.OK).body("Fornecedor Deletado com Sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid FornecedoresDTO fornecedoresDTO) {
        Optional<Fornecedores> fornecedores = repository.findById(id);
        if(fornecedores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem fornecedores");
        }
        Fornecedores UpdateFornecedores = new Fornecedores();
        BeanUtils.copyProperties(fornecedoresDTO,UpdateFornecedores);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(UpdateFornecedores));

    }

}
