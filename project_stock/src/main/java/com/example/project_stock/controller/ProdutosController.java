package com.example.project_stock.controller;


import com.example.project_stock.domain.produtos.Produtos;
import com.example.project_stock.domain.produtos.ProdutosDTO;
import com.example.project_stock.repositories.ProdutosRepository;
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


@RestController()
@RequestMapping("produtos")
public class ProdutosController {

    @Autowired
    ProdutosRepository repository;


    @GetMapping
    public ResponseEntity<List<Produtos>> getAllProdutos() {
     List<Produtos> produtos = repository.findAll();
     if (!produtos.isEmpty()) {
         produtos.forEach(produto -> {
             String id = produto.getId();
             produto.add(linkTo(methodOn(ProdutosController.class).findById(id)).withSelfRel());
         });
     }
     return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Optional<Produtos> produto = repository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        produto.get().add(linkTo(methodOn(ProdutosController.class).getAllProdutos()).withRel("Lista de Produtos"));
        return ResponseEntity.status(HttpStatus.OK).body(produto.get());

    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProdutosDTO produtosDTO) {
        Produtos produtos = new Produtos();
        BeanUtils.copyProperties(produtosDTO, produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtos));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Optional<Produtos> produto = repository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        repository.delete(produto.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto removido.");

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid ProdutosDTO produtosDTO) {
        Optional<Produtos> produto = repository.findById(id);
        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        Produtos updateProdutos = produto.get();
        BeanUtils.copyProperties(produtosDTO, updateProdutos);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(updateProdutos));
    }


}
