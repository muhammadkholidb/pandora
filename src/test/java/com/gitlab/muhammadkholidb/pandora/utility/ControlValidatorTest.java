package com.gitlab.muhammadkholidb.pandora.utility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import com.gitlab.muhammadkholidb.pandora.JavaFXTestBase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javafx.scene.control.TextField;

@MockitoSettings(strictness = Strictness.WARN)
@ExtendWith(MockitoExtension.class)
public class ControlValidatorTest extends JavaFXTestBase {

    private ResourceBundle resourceBundle;
    private ControlValidator controlValidator;

    @BeforeEach
    void setUp() {
        resourceBundle = new ListResourceBundle() {
            @Override
            protected Object[][] getContents() {
                return new Object[][] { { "EMPTY", "Empty" } };
            }
        };
        controlValidator = new ControlValidator(resourceBundle);
    }

    @Test
    void validateBlank_withTextField_withStringMessage_shouldBeInvalid() {
        controlValidator.validateBlank(new TextField(), "Empty");
        ValidationResult result = controlValidator.getResult();
        assertThat(result.isValid(), is(false));
        assertThat(result.getMessages(), contains("Empty"));
    }

    @Test
    void validateBlank_withTextField_withIMessage_shouldBeInvalid() {
        controlValidator.validateBlank(new TextField(), TestMessage.EMPTY);
        ValidationResult result = controlValidator.getResult();
        assertThat(result.isValid(), is(false));
        assertThat(result.getMessages(), contains("Empty"));
    }

    private enum TestMessage implements IMessage {
        EMPTY
    }

}
