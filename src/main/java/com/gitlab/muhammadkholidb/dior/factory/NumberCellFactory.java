package com.gitlab.muhammadkholidb.dior.factory;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class NumberCellFactory<E, T extends Number> implements Callback<TableColumn<E, T>, TableCell<E, T>> {

    public static final int DEFAULT_DECIMAL_DIGIT = 0;

    private static final Locale BAHASA_ISO_2 = new Locale("id");
    private static final Locale BAHASA_ISO_3 = new Locale("ind");

    private int decimalDigit;
    private char decimalSeparator;
    private char thousandSeparator;

    public NumberCellFactory(int decimalDigit, Locale locale) {
        boolean isBahasa = locale.equals(BAHASA_ISO_2) || locale.equals(BAHASA_ISO_3);
        this.decimalDigit = decimalDigit;
        this.decimalSeparator = isBahasa ? ',' : '.';
        this.thousandSeparator = isBahasa ? '.' : ',';
    }

    public NumberCellFactory(Locale locale) {
        this(DEFAULT_DECIMAL_DIGIT, locale);
    }

    @Override
    public TableCell<E, T> call(TableColumn<E, T> param) {

        return new TableCell<E, T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                if (item == getItem()) {
                    return;
                }
                super.updateItem(item, empty);
                if (item == null) {
                    super.setText(null);
                    super.setGraphic(null);
                } else {
                    DecimalFormat df = new DecimalFormat();
                    DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
                    customSymbol.setDecimalSeparator(decimalSeparator);
                    customSymbol.setGroupingSeparator(thousandSeparator);
                    df.setDecimalFormatSymbols(customSymbol);
                    df.setMinimumFractionDigits(decimalDigit);
                    df.setGroupingUsed(true);
                    df.setRoundingMode(RoundingMode.HALF_UP);
                    String formatted = df.format(item);
                    super.setText(formatted);
                }
            }
        };
    }

}
