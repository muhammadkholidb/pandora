package com.gitlab.muhammadkholidb.pandora.validator;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import javafx.scene.control.Control;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class BaseValidator<T> implements Validator<T> {

    private String message;
    private Severity severity;

    public BaseValidator(String message) {
        this.message = message;
        this.severity = Severity.ERROR;
    }

    protected abstract boolean getCondition(Control control, T value);

    @Override
    public ValidationResult apply(Control control, T value) {
        return ValidationResult.fromMessageIf(control, message, severity, getCondition(control, value));
    }

}
