package com.co.cfd.springboot.app.springbootcrud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistDbValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IsExistsDb {
    
    String message() default "ya existe en la Base de Datos!!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
