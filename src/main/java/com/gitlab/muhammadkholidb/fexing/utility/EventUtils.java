package com.gitlab.muhammadkholidb.fexing.utility;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EventUtils {

    private EventUtils() {
    }

    public static boolean isDoubleClick(MouseEvent event) {
        return event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2;
    }

    public static boolean isEnter(KeyEvent event) {
        return event.getCode() == KeyCode.ENTER;
    }

}
