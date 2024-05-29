package com.co.cfd.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.co.cfd.springboot.app.springbootcrud.entities.Product;

public interface IProductRepository extends CrudRepository<Product, Long> {
    boolean existsByInfo(String info);
}
