package com.adobe.bookstore.adapter.web.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FormatValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatConstraint {
	
    String message() default "Invalid format. It has to be JSON or CSV.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
