package com.gitlab.muhammadkholidb.pandora.validator;

import org.apache.commons.lang3.StringUtils;
import org.controlsfx.validation.Severity;

import javafx.scene.control.Control;

public class BlankValidator extends BaseValidator<String> {

    public BlankValidator(String message, Severity severity) {
        super(message, severity);
    }

    public BlankValidator(String message) {
        super(message);
    }

    @Override
    protected boolean getCondition(Control control, String value) {
        return StringUtils.isBlank(value);
    }
    
}
