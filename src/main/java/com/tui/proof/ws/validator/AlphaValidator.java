package com.tui.proof.ws.validator;

import com.tui.proof.annotation.AlphaConstraint;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphaValidator implements
        ConstraintValidator<AlphaConstraint, String> {

    @Override
    public boolean isValid(final String name,
                           final ConstraintValidatorContext cxt) {
        return StringUtils.isEmpty(name) || StringUtils.isAlpha(name);
    }

}
