package com.co.cfd.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import com.co.cfd.springboot.app.springbootcrud.entities.Product;

public interface IProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product product);
    Optional<Product> delete(Long id);
    boolean existsByInfo(String info);
}
