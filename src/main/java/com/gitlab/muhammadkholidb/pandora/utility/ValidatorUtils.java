package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DomainValidator;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.Validator;

public class ValidatorUtils {

    private ValidatorUtils() {
    }

    public static void createBlankCheck(Validator validator, String message, TextField... tfs) {
        Arrays.stream(tfs)
                .forEach(tf -> {
                    String key = key(tf);
                    validator.createCheck().dependsOn(key, tf.textProperty()).withMethod(ctx -> {
                        if (StringUtils.isBlank(ctx.get(key))) {
                            ctx.error(message);
                        }
                    }).immediate().decorates(tf);
                });
    }

    public static void createEmailCheck(Validator validator, String message, TextField... tfs) {
        Arrays.stream(tfs)
                .forEach(tf -> {
                    String key = key(tf);
                    validator.createCheck().dependsOn(key, tf.textProperty()).withMethod(ctx -> {
                        String value = ctx.get(key);
                        if (StringUtils.isNotBlank(value) && !GenericValidator.isEmail(value)) {
                            ctx.error(message);
                        }
                    }).immediate().decorates(tf);
                });
    }

    public static void createDomainCheck(Validator validator, String message, TextField... tfs) {
        Arrays.stream(tfs)
                .forEach(tf -> {
                    String key = key(tf);
                    validator.createCheck().dependsOn(key, tf.textProperty()).withMethod(ctx -> {
                        String value = ctx.get(key);
                        if (StringUtils.isNotBlank(value) && !DomainValidator.getInstance().isValid(value)) {
                            ctx.error(message);
                        }
                    }).immediate().decorates(tf);
                });
    }

    public static void createSelectionCheck(Validator validator, String message, ComboBox<?>... cbs) {
        Arrays.stream(cbs).forEach(
                cb -> {
                    String key = key(cb);
                    validator.createCheck().dependsOn(key, cb.selectionModelProperty()).withMethod(ctx -> {
                        SingleSelectionModel<?> selection = ctx.get(key);
                        if (selection.getSelectedItem() == null) {
                            ctx.error(message);
                        }
                    }).immediate().decorates(cb);
                });
    }

    public  static String key(Control control) {
        return StringUtils.defaultIfBlank(control.getId(), Integer.toHexString(control.hashCode()));
    }

}
