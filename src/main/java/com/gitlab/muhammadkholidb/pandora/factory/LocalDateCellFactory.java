package com.gitlab.muhammadkholidb.pandora.factory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class LocalDateCellFactory<E> implements Callback<TableColumn<E, LocalDate>, TableCell<E, LocalDate>> {

    private String pattern;

    public LocalDateCellFactory(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public TableCell<E, LocalDate> call(TableColumn<E, LocalDate> param) {

        return new TableCell<E, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
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
