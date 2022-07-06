package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;
import java.util.function.BiConsumer;

import com.gitlab.muhammadkholidb.pandora.formatter.DigitFormatter;

import org.apache.commons.lang3.ArrayUtils;

import javafx.scene.control.TextField;

public final class TextFieldUtils {

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
     * Listens to TextField text change, accepts old value and new value.
     * 
     * @param consumer the consumer of old value and new value
     * @param tf       the TextField to listen to text change
     * @param tfs      other TextFields to listen to text change
     */
    public static void onTextChanged(BiConsumer<String, String> consumer, TextField tf, TextField... tfs) {
        for (TextField textField : ArrayUtils.addAll(tfs, tf)) {
            textField.textProperty().addListener((o, ov, nv) -> consumer.accept(ov, nv));
        }
    }

}
