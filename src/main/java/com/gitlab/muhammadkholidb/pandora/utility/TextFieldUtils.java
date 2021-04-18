package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;

import com.gitlab.muhammadkholidb.pandora.formatter.DigitFormatter;

import javafx.scene.control.TextField;

public class TextFieldUtils {

    private TextFieldUtils() {
    }

    public static void setDigitTextFields(TextField... textFields) {
        Arrays.asList(textFields).forEach(tf -> tf.setTextFormatter(new DigitFormatter()));
    }

}
