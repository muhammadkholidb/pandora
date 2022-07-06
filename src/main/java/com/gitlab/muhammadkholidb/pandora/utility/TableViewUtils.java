package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public final class TableViewUtils {

    private TableViewUtils() {
    }

    public static <M, T> void setColumnValue(TableColumn<M, T> col, Function<M, T> valueFunction) {
        col.setCellValueFactory(data -> new SimpleObjectProperty<>(valueFunction.apply(data.getValue())));
    }

    public static <M, T> void setColumnStyle(TableColumn<M, T> col, String style) {
        col.setStyle(style);
    }

    public static <M, T> void initTableColumn(
            TableColumn<M, T> col,
            Callback<TableColumn<M, T>, TableCell<M, T>> cellFactory,
            Function<M, T> valueFunction,
            String style) {
        if (cellFactory != null) {
            col.setCellFactory(cellFactory);
        }
        if (valueFunction != null) {
            setColumnValue(col, valueFunction);
        }
        if (StringUtils.isNotBlank(style)) {
            setColumnStyle(col, style);
        }
    }

    public static <M, T> void initTableColumn(
            TableColumn<M, T> col,
            Callback<TableColumn<M, T>, TableCell<M, T>> cellFactory,
            Function<M, T> valueFunction) {
        initTableColumn(col, cellFactory, valueFunction, null);
    }

    public static <M, T> void initTableColumn(TableColumn<M, T> col, Function<M, T> valueFunction, String style) {
        initTableColumn(col, null, valueFunction, style);
    }

    public static boolean hasItemSelected(TableView<?> table) {
        return !table.getSelectionModel().isEmpty();
    }

    public static <T> T getSelectedItem(TableView<T> table) {
        return table.getSelectionModel().getSelectedItem();
    }

    public static <T> ObservableList<T> getSelectedItems(TableView<T> table) {
        return table.getSelectionModel().getSelectedItems();
    }

    public static int getSelectedIndex(TableView<?> table) {
        return table.getSelectionModel().getSelectedIndex();
    }

    public static ObservableList<Integer> getSelectedIndices(TableView<?> table) {
        return table.getSelectionModel().getSelectedIndices();
    }

    public static <T> T getItemByIndex(int index, TableView<T> table) {
        return table.getItems().get(index);
    }

    public static <T> int getItemIndex(T item, TableView<T> table) {
        return getItemIndex((Predicate<T>) t -> t.equals(item), table);
    }

    public static <T> int getItemIndex(Predicate<T> itemPredicate, TableView<T> table) {
        ObservableList<T> items = table.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (itemPredicate.test(items.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <T> void sort(TableView<T> table, TableColumn<T, ?> column, SortType sortType) {
        column.setSortType(sortType);
        table.getSortOrder().add(column);
        table.sort();
    }

    public static <T> void sortAscending(TableView<T> table, TableColumn<T, ?> column) {
        sort(table, column, SortType.ASCENDING);
    }

    public static <T> void sortDescending(TableView<T> table, TableColumn<T, ?> column) {
        sort(table, column, SortType.DESCENDING);
    }

}
