package com.tui.proof.ws.validator;

import com.tui.proof.annotation.PilotesConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PilotesValidator implements
        ConstraintValidator<PilotesConstraint, Integer> {

    public static final int INT_VALUE_5 = 5;
    public static final int INT_VALUE_0 = 0;
    public static final int INT_VALUE_15 = 15;

    @Override
    public boolean isValid(final Integer pilotes,
                           final ConstraintValidatorContext cxt) {
        return pilotes != null && (pilotes % INT_VALUE_5 == INT_VALUE_0)
                && pilotes > INT_VALUE_0 && pilotes <= INT_VALUE_15;
    }

}
