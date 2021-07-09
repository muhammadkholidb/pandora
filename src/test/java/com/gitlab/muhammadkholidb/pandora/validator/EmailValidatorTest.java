package com.gitlab.muhammadkholidb.pandora.validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.gitlab.muhammadkholidb.pandora.JavaFXTestBase;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.junit.jupiter.api.Test;

import javafx.scene.control.TextField;

public class EmailValidatorTest extends JavaFXTestBase {

    @Test
    void testApply_validEmail_shouldHasNoErrors() {
        EmailValidator emailValidator = new EmailValidator("invalid email");
        ValidationResult result = emailValidator.apply(new TextField(), "test@email.com");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(), empty());
        assertThat(result.getWarnings(), empty());
    }

    @Test
    void testApply_invalidEmail_shouldHasErrors() {
        EmailValidator emailValidator = new EmailValidator("invalid email");
        ValidationResult result = emailValidator.apply(new TextField(), "an email");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(),
                contains(allOf(hasProperty("text", is("invalid email")), hasProperty("severity", is(Severity.ERROR)))));
    }

    @Test
    void testApply_invalidEmail_severityWarning_shouldHasWarnings() {
        EmailValidator emailValidator = new EmailValidator("invalid email", Severity.WARNING);
        ValidationResult result = emailValidator.apply(new TextField(), "an email");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(), empty());
        assertThat(result.getWarnings(), contains(
                allOf(hasProperty("text", is("invalid email")), hasProperty("severity", is(Severity.WARNING)))));
    }

}
