package com.gitlab.muhammadkholidb.dior.utility;

import java.util.function.Supplier;

import com.gitlab.muhammadkholidb.dior.converter.DefaultStringConverterAdapter;
import com.gitlab.muhammadkholidb.dior.model.BasicComboBoxModel;

import org.apache.commons.lang3.ArrayUtils;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class ComboBoxUtils {

    private ComboBoxUtils() {
    }

    public static boolean hasItemSelected(ComboBox<?> cb) {
        return cb.getSelectionModel().getSelectedItem() != null;
    }

    public static <T> T getSelectedItem(ComboBox<T> cb) {
        return cb.getSelectionModel().getSelectedItem();
    }

    public static <T> void allowSpaceOnEditor(ComboBox<T> cb) {
        ComboBoxListViewSkin<T> comboBoxListViewSkin = new ComboBoxListViewSkin<>(cb);
        comboBoxListViewSkin.getPopupContent().addEventFilter(KeyEvent.ANY, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        cb.setSkin(comboBoxListViewSkin);
    }

    public static <T> void initAutoComplete(ComboBox<T> cb, EventHandler<KeyEvent> keyEvent,
            StringConverter<T> converter, Supplier<T> selectedSupplier) {
        if (!cb.isEditable()) {
            return;
        }
        allowSpaceOnEditor(cb);
        cb.getEditor().setOnKeyReleased(keyEvent);
        cb.setConverter(converter);
        if (selectedSupplier != null) {
            select(cb, selectedSupplier);
        }
    }

    public static <T> void initAutoComplete(ComboBox<T> cb, EventHandler<KeyEvent> keyEvent,
            StringConverter<T> converter) {
        initAutoComplete(cb, keyEvent, converter, null);
    }

    public static <T> void select(ComboBox<T> cb, Supplier<T> itemSupplier) {
        T item = itemSupplier.get();
        if (!cb.getItems().contains(item)) {
            cb.getItems().add(item);
        }
        cb.getSelectionModel().select(item);
    }

    public static <T> void selectIndex(ComboBox<T> cb, int index) {
        cb.getSelectionModel().clearAndSelect(index);
    }

    public static <T> void clearSelection(ComboBox<T> cb) {
        cb.getSelectionModel().clearSelection();
    }

    @SuppressWarnings("unchecked")
    public static <T> void init(ComboBox<T> cb, StringConverter<T> converter, T... data) {
        if (ArrayUtils.isNotEmpty(data)) {
            cb.setItems(FXCollections.observableArrayList(data));
        }
        cb.setConverter(converter);
    }

    public static void initBasic(ComboBox<BasicComboBoxModel> cb, BasicComboBoxModel... data) {
        init(cb, new DefaultStringConverterAdapter<>(cb) {

            @Override
            protected String getDisplayText(BasicComboBoxModel vm) {
                return vm.getLabel();
            }

        }, data);
    }

}
