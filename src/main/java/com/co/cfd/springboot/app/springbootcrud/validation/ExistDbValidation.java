package com.co.cfd.springboot.app.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.co.cfd.springboot.app.springbootcrud.services.IProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistDbValidation implements ConstraintValidator<IsExistsDb, String>{

    @Autowired
    private IProductService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !service.existsByInfo(value);
    }

}
