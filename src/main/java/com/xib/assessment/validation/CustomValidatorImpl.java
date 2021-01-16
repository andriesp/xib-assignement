package com.xib.assessment.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomValidatorImpl implements CustomValidator {
    private Validator validator;

    @Override
    public void validate(Object object, Class<?>... groups) {
        if (Objects.isNull(object)) throw new ValidationException("Object cannot be null");

        Set<ConstraintViolation<Object>> violations = getValidatorInstance().validate(object, groups);
        processViolation(violations);
    }

    private void processViolation(Set<ConstraintViolation<Object>> violations) {
        if (CollectionUtils.isEmpty(violations)) return;
        List<String> errors = violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        throw new ValidationException(errors.toString());
    }

    @Override
    public Validator getValidatorInstance() {

        if (Objects.nonNull(validator)) return validator;

        validator = Validation.buildDefaultValidatorFactory().getValidator();
        return validator;
    }


}
