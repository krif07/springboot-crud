package com.co.cfd.springboot.app.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.cfd.springboot.app.springbootcrud.entities.Product;
import com.co.cfd.springboot.app.springbootcrud.services.IProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/products")
public class ProductController extends ValidationController {

    @Autowired
    private IProductService service;

    @GetMapping
    public List<Product> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Product> pOptional = service.findById(id);
        if(pOptional.isPresent()) {
            return ResponseEntity.ok().body(pOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        Product productDb = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDb);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, 
            BindingResult result, @PathVariable Long id) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Product> pOptional = service.update(id, product);
        if(pOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(pOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        
        Optional<Product> pDeleteOptional = service.delete(id);
        if(pDeleteOptional.isPresent()){
            return ResponseEntity.ok(pDeleteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
