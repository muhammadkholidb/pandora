package com.gitlab.muhammadkholidb.pandora.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleComboBoxModel {
    private Object value;
    private String label;

    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        return (T) this.value;
    }
}
