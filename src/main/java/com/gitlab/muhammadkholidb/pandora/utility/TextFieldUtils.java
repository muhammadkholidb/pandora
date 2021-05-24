package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;

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

    public static void setTextNull(TextField... textFields) {
        setText(null, textFields);
    }

    public static void setText(String text, TextField... textFields) {
        if (ArrayUtils.isNotEmpty(textFields)) {
            Arrays.asList(textFields).forEach(tf -> tf.setText(text));
        }
    }

}
