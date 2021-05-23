package com.gitlab.muhammadkholidb.pandora.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.controlsfx.validation.Severity;

import javafx.scene.control.Control;

public class EmailValidator extends BaseValidator<String> {

    public EmailValidator(String message, Severity severity) {
        super(message, severity);
    }

    public EmailValidator(String message) {
        super(message);
    }

    @Override
    protected boolean getCondition(Control control, String value) {
        return StringUtils.isNotBlank(value) && !GenericValidator.isEmail(value);
    }

}
