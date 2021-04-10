package com.gitlab.muhammadkholidb.dior.utility;

import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ValidationResult {

    @Getter
    private Set<IMessage> messages;

    public void addError(IMessage message) {
        if (messages == null) {
            messages = new HashSet<>();
        }
        messages.add(message);
    }

    public boolean hasError() {
        return messages != null && !messages.isEmpty();
    }

}
