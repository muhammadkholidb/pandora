package com.gitlab.muhammadkholidb.pandora.validator;

import org.apache.commons.lang3.StringUtils;
import org.controlsfx.validation.Severity;

import javafx.scene.control.Control;

public class WhitespaceValidator extends BaseValidator<String> {

    public WhitespaceValidator(String message, Severity severity) {
        super(message, severity);
    }

    public WhitespaceValidator(String message) {
        super(message);
    }

    @Override
    protected boolean getCondition(Control control, String value) {
        return StringUtils.isWhitespace(value);
    }
    
}
