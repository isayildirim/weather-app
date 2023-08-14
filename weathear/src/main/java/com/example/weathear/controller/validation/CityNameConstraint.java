package com.example.weathear.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER } )
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Constraint(
        validatedBy = { CityParameterValidator.class}
)
public @interface CityNameConstraint {

    String message() default "Invalid city name";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
