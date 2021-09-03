package com.gitlab.muhammadkholidb.pandora.utility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.control.TextField;

@ExtendWith(ApplicationExtension.class)
public class CommonValidatorTest {

    @Test
    void testCreateEmailValidator_shouldHasNoErrors() {
        Validator<String> validator = CommonValidator.createEmailValidator("invalid email");
        ValidationResult result = validator.apply(new TextField(), "test@email.com");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(), empty());
        assertThat(result.getWarnings(), empty());
    }

    @Test
    void testCreateEmailValidator_shouldHasErrors() {
        Validator<String> validator = CommonValidator.createEmailValidator("invalid email");
        ValidationResult result = validator.apply(new TextField(), "an email");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(),
                contains(allOf(hasProperty("text", is("invalid email")), hasProperty("severity", is(Severity.ERROR)))));
    }

    @Test
    void testCreateDomainValidator_shouldHasNoErrors() {
        Validator<String> validator = CommonValidator.createDomainValidator("invalid domain");
        ValidationResult result = validator.apply(new TextField(), "www.google.com");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(), empty());
        assertThat(result.getWarnings(), empty());
    }

    @Test
    void testCreateDomainValidator_shouldHasErrors() {
        Validator<String> validator = CommonValidator.createDomainValidator("invalid domain");
        ValidationResult result = validator.apply(new TextField(), "domain");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(),
                contains(allOf(hasProperty("text", is("invalid domain")), hasProperty("severity", is(Severity.ERROR)))));
    }

    @Test
    void testCreateWhitespaceValidator_shouldHasNoErrors() {
        Validator<String> validator = CommonValidator.createWhitespaceValidator("cannot be empty");
        ValidationResult result = validator.apply(new TextField(), "text");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(), empty());
        assertThat(result.getWarnings(), empty());
    }

    @Test
    void testCreateWhitespaceValidator_shouldHasErrors() {
        Validator<String> validator = CommonValidator.createWhitespaceValidator("cannot be empty");
        ValidationResult result = validator.apply(new TextField(), "    ");
        assertThat(result, notNullValue());
        assertThat(result.getErrors(),
                contains(allOf(hasProperty("text", is("cannot be empty")), hasProperty("severity", is(Severity.ERROR)))));
    }

}
