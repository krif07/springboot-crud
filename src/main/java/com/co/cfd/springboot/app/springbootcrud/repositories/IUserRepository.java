package com.co.cfd.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.co.cfd.springboot.app.springbootcrud.entities.User;

public interface IUserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
}
