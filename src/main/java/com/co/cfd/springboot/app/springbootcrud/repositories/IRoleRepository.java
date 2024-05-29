package com.co.cfd.springboot.app.springbootcrud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.co.cfd.springboot.app.springbootcrud.entities.Role;

public interface IRoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
