package com.co.cfd.springboot.app.springbootcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.cfd.springboot.app.springbootcrud.entities.Product;
import com.co.cfd.springboot.app.springbootcrud.repositories.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
    
    @Autowired
    private IProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }
    
    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> pOptional = repository.findById(id);
        if(pOptional.isPresent()){
            Product p = pOptional.orElseThrow();
            p.setName(product.getName());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setInfo(product.getInfo());
            return Optional.of(repository.save(p));
        }
        return pOptional;
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> pOptional = repository.findById(id);
        pOptional.ifPresent(p -> {
            repository.delete(p);
        });
        return pOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByInfo(String info) {
       return repository.existsByInfo(info);
    }
}
