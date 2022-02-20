package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DomainValidator;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Getter;

public class ControlValidator {

    @Getter
    private ValidationResult result = new ValidationResult();
    
    private Translator translator;

    public ControlValidator() {}

    public ControlValidator(ResourceBundle resourceBundle) {
        this.translator = new Translator(resourceBundle);
    }

    public void validateBlank(TextField tf, String message) {
        if (StringUtils.isBlank(tf.getText())) {
            result.addMessage(message);
        }
    }

    public void validateBlank(TextField tf, IMessage message) {
        validateBlank(tf, translator.translate(message));
    }

    public void validateEmail(TextField tf, String message) {
        String value = tf.getText();
        if (StringUtils.isNotBlank(value) && !GenericValidator.isEmail(value)) {
            result.addMessage(message);
        }
    }

    public void validateEmail(TextField tf, IMessage message) {
        validateEmail(tf, translator.translate(message));
    }

    public void validateDomain(TextField tf, String message) {
        String value = tf.getText();
        if (StringUtils.isNotBlank(value) && !DomainValidator.getInstance().isValid(value)) {
            result.addMessage(message);
        }
    }

    public void validateDomain(TextField tf, IMessage message) {
        validateDomain(tf, translator.translate(message));
    }

    public void validateSelected(ComboBox<?> cb, String message) {
        if (!ComboBoxUtils.hasItemSelected(cb)) {
            result.addMessage(message);
        }
    }

    public void validateSelected(ComboBox<?> cb, IMessage message) {
        validateSelected(cb, translator.translate(message));
    }

    public void validateCustom(BooleanSupplier supplier, String message) {
        if (supplier.getAsBoolean()) {
            result.addMessage(message);
        }
    }

    public void validateCustom(BooleanSupplier supplier, IMessage message) {
        validateCustom(supplier, translator.translate(message));
    }

}
