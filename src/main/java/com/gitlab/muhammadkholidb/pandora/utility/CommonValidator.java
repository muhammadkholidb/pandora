package com.gitlab.muhammadkholidb.pandora.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DomainValidator;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

public class CommonValidator {

    private CommonValidator() {
    }

    public static Validator<String> createEmailValidator(final String message) {
        return (control, value) -> {
            boolean condition = StringUtils.isNotBlank(value) && !GenericValidator.isEmail(value);
            return ValidationResult.fromErrorIf(control, message, condition);
        };
    }

    public static Validator<String> createDomainValidator(final String message) {
        return (control, value) -> {
            boolean condition = StringUtils.isNotBlank(value) && !DomainValidator.getInstance().isValid(value);
            return ValidationResult.fromErrorIf(control, message, condition);
        };
    }

    public static Validator<String> createWhitespaceValidator(final String message) {
        return (control, value) -> ValidationResult.fromErrorIf(control, message, StringUtils.isWhitespace(value));
    }

}
