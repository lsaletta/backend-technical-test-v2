package com.tui.proof.annotation;

import com.tui.proof.ws.validator.AlphaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = AlphaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphaConstraint {
    String message() default "Validation fail: The field only allows alphabetic characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
