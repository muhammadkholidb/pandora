package com.gitlab.muhammadkholidb.pandora.utility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.WARN)
@ExtendWith(MockitoExtension.class)
public class TranslatorTest {

    private ResourceBundle resourceBundle;
    private Translator translator;

    @BeforeEach
    void setUp() {
        resourceBundle = new ListResourceBundle() {
            @Override
            protected Object[][] getContents() {
                return new Object[][] { { "MSG", "Message" } };
            }
        };
        translator = new Translator(resourceBundle);
    }

    @Test
    void translate_withStringCode_withDefaultMessage_shouldSucceed() {
        String result = translator.translate("MSG", "any");
        assertThat(result, is("Message"));
        result = translator.translate("ERR", "any");
        assertThat(result, is("any"));
    }

    @Test
    void translate_withStringCode_withoutDefaultMessage_shouldSucceed() {
        String result = translator.translate("MSG");
        assertThat(result, is("Message"));
        result = translator.translate("ERR");
        assertThat(result, is("ERR"));
    }

    @Test
    void translate_withIMessageCode_withDefaultMessage_shouldSucceed() {
        String result = translator.translate(TestMessage.MSG, "any");
        assertThat(result, is("Message"));
        result = translator.translate(TestMessage.ERR, "any");
        assertThat(result, is("any"));
    }

    @Test
    void translate_withIMessageCode_withoutDefaultMessage_shouldSucceed() {
        String result = translator.translate(TestMessage.MSG);
        assertThat(result, is("Message"));
        result = translator.translate(TestMessage.ERR);
        assertThat(result, is("ERR"));
    }

    private enum TestMessage implements IMessage {
        MSG,
        ERR
    }

}
