package com.tui.proof.ws.validator;

import com.tui.proof.annotation.PhoneConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements
        ConstraintValidator<PhoneConstraint, String> {

    @Override
    public boolean isValid(final String phone,
                           final ConstraintValidatorContext cxt) {
        String regex = "^\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

}
