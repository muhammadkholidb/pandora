package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;
import java.util.function.BiConsumer;

import com.gitlab.muhammadkholidb.pandora.formatter.DigitFormatter;

import org.apache.commons.lang3.ArrayUtils;

import javafx.scene.control.TextField;

public class TextFieldUtils {

    private TextFieldUtils() {
    }

    public static void setDigitTextFields(TextField... textFields) {
        if (ArrayUtils.isNotEmpty(textFields)) {
            Arrays.asList(textFields).forEach(tf -> tf.setTextFormatter(new DigitFormatter()));
        }
    }

    public static void setTextEmpty(TextField... textFields) {
        setText("", textFields);
    }

    public static void setText(String text, TextField... textFields) {
        if (ArrayUtils.isNotEmpty(textFields)) {
            Arrays.asList(textFields).forEach(tf -> tf.setText(text));
        }
    }

    /**
     * Listens to TextField text changes, accepts old value and new value.
     * 
     * @param tf the TextField to listen to text changes
     * @param consumer the consumer of old value and new value
     */
    public static void onTextChanged(TextField tf, BiConsumer<String, String> consumer) {
        tf.textProperty().addListener((o, ov, nv) -> consumer.accept(ov, nv));
    }

}
