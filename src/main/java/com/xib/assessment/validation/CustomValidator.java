package com.xib.assessment.validation;

import javax.validation.Validator;

public interface CustomValidator {
    void validate(Object object, Class<?>... groups);

    Validator getValidatorInstance();
}
