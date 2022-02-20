package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationResult {

    private List<String> messages = new ArrayList<>();

    public boolean isValid() {
        return messages.isEmpty();
    }

    public void addMessage(String message) {
        messages.add(message);
    }

}
