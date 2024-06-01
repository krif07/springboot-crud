package com.co.cfd.springboot.app.springbootcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUserNameValidation implements ConstraintValidator<IExistByUserName, String>{

    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
       return !userService.existsByUsername(username);
    }

}
