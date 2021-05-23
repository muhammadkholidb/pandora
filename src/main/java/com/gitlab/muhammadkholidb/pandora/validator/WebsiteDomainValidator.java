package com.gitlab.muhammadkholidb.pandora.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.controlsfx.validation.Severity;

import javafx.scene.control.Control;

public class WebsiteDomainValidator extends BaseValidator<String> {

    public WebsiteDomainValidator(String message, Severity severity) {
        super(message, severity);
    }

    public WebsiteDomainValidator(String message) {
        super(message);
    }

    @Override
    protected boolean getCondition(Control control, String value) {
        return StringUtils.isNotBlank(value) && !DomainValidator.getInstance().isValid(value);
    }
    
}
