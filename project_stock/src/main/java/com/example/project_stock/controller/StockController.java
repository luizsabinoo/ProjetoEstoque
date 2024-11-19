package com.example.project_stock.controller;


import com.example.project_stock.domain.estoque.Estoque;
import com.example.project_stock.domain.estoque.EstoqueDTO;
import com.example.project_stock.repositories.StockRepository;
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
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping
    public ResponseEntity<List<Estoque>> findAll() {
        List<Estoque> estoques = stockRepository.findAll();
        if (estoques.isEmpty()) {
            estoques.forEach(estoque -> {
                String id = estoque.getId();
                estoque.add(linkTo(methodOn(StockController.class).getStock(id)).withSelfRel());
            });
        }
        return ResponseEntity.status(HttpStatus.OK).body(estoques);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getStock(@PathVariable String id) {
        Optional<Estoque> estoque = stockRepository.findById(id);
        if(estoque.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
        }
        estoque.get().add(linkTo(methodOn(StockController.class).getStock(id)).withRel("Stock list"));
        return ResponseEntity.status(HttpStatus.OK).body(estoque);

    }

    @PostMapping
    public ResponseEntity<Estoque> saveStock(@RequestBody @Valid EstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();
        BeanUtils.copyProperties(estoqueDTO, estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoque);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable String id) {
        Optional<Estoque> estoque = stockRepository.findById(id);
        if(estoque.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");
        }
        stockRepository.delete(estoque.get());
        return ResponseEntity.status(HttpStatus.OK).body("Stock deleted successfully");

    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateStock(@PathVariable String id ,@RequestBody @Valid EstoqueDTO estoqueDTO) {
        Optional<Estoque> estoque = stockRepository.findById(id);
        if(estoque.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found");

        }
        Estoque UpdateStock = new Estoque();
        BeanUtils.copyProperties(estoqueDTO, UpdateStock);
        return ResponseEntity.status(HttpStatus.OK).body(UpdateStock);
    }

}

