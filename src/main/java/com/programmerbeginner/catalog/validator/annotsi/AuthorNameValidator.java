package com.programmerbeginner.catalog.validator.annotsi;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName,String> {
    @Override
    public boolean isValid(String authorName, ConstraintValidatorContext constraintValidatorContext) {
        return !authorName.equalsIgnoreCase("pidi baik");
    }
}
