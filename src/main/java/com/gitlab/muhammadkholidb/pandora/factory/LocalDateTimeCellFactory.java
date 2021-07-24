package com.gitlab.muhammadkholidb.pandora.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class LocalDateTimeCellFactory<E> implements Callback<TableColumn<E, LocalDateTime>, TableCell<E, LocalDateTime>> {

    private String pattern;

    public LocalDateTimeCellFactory(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public TableCell<E, LocalDateTime> call(TableColumn<E, LocalDateTime> param) {

        return new TableCell<E, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                if (item == getItem()) {
                    return;
                }
                super.updateItem(item, empty);
                if (item == null) {
                    super.setText(null);
                    super.setGraphic(null);
                } else {
                    super.setText(item.format(DateTimeFormatter.ofPattern(pattern)));
                }
            }
        };
    }

}
