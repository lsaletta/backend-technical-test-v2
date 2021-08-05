package com.tui.proof.annotation;

import com.tui.proof.ws.validator.PilotesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = PilotesValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PilotesConstraint {
    String message() default "Validation fail: The field only allows the following values [5, 10, 15]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
