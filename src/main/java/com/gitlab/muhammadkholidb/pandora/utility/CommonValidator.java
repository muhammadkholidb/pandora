package com.gitlab.muhammadkholidb.pandora.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class CommonValidator {

    private CommonValidator() {
    }

    public static Validator<String> createBlankValidator(final String message, final Severity severity) {
        return (control, value) -> ValidationResult.fromMessageIf(control, message, severity,
                StringUtils.isBlank(value));
    }

    public static Validator<String> createBlankValidator(final String message) {
        return createBlankValidator(message, Severity.ERROR);
    }

    public static Validator<String> createEmailValidator(final String message, final Severity severity) {
        return (control, value) -> {
            boolean condition;
            if (ValidationSupport.isRequired(control)) {
                condition = !EmailValidator.getInstance().isValid(value);
            } else {
                condition = StringUtils.isNotBlank(value) && !EmailValidator.getInstance().isValid(value);
            }
            return ValidationResult.fromMessageIf(control, message, severity, condition);
        };
    }

    public static Validator<String> createEmailValidator(final String message) {
        return createEmailValidator(message, Severity.ERROR);
    }

    public static Validator<String> createDomainValidator(final String message, final Severity severity) {
        return (control, value) -> {
            boolean condition;
            if (ValidationSupport.isRequired(control)) {
                condition = !DomainValidator.getInstance().isValid(value);
            } else {
                condition = StringUtils.isNotBlank(value) && !DomainValidator.getInstance().isValid(value);
            }
            return ValidationResult.fromMessageIf(control, message, severity, condition);
        };
    }

    public static Validator<String> createDomainValidator(final String message) {
        return createDomainValidator(message, Severity.ERROR);
    }

}
