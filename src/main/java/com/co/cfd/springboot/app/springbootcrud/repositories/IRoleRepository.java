package com.co.cfd.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.co.cfd.springboot.app.springbootcrud.entities.Role;

public interface IRoleRepository extends CrudRepository<Role, Long> {

}
