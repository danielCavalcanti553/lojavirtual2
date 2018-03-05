package com.tcc.lojavirtual.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import com.tcc.lojavirtual.services.validation.ClienteNewValidation;

@Constraint(validatedBy=ClienteUpdateValidation.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteUpdate {
	
	String message() default "Erro de Valição";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};

}
