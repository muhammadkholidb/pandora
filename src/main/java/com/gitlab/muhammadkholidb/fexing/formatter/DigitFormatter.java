package com.gitlab.muhammadkholidb.fexing.formatter;

import javafx.scene.control.TextFormatter;

public class DigitFormatter extends TextFormatter<String> {

    public DigitFormatter() {
        super(change -> change.getText().matches("\\d*") ? change : null);
    }

}
