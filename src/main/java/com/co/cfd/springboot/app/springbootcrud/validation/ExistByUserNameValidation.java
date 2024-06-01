package com.co.cfd.springboot.app.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.co.cfd.springboot.app.springbootcrud.services.IUserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUserNameValidation implements ConstraintValidator<IExistByUserName, String>{

    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(userService == null) {
            return true;
        }
        return !userService.existsByUsername(username);
    }

}
