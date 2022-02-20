package com.gitlab.muhammadkholidb.pandora.utility;

import java.util.ResourceBundle;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Translator {

    private ResourceBundle resourceBundle;

    public String translate(String messageCode, String defaultMessage) {
        try {
            return resourceBundle.getString(messageCode);
        } catch (Exception e) {
            log.warn("Failed to translate message code '{}': {}", messageCode, e.toString());
            return defaultMessage;
        }
    }

    public String translate(String messageCode) {
        return translate(messageCode, messageCode);
    }

    public String translate(IMessage messageCode, String defaultMessage) {
        return translate(messageCode.toString(), defaultMessage);
    }

    public String translate(IMessage messageCode) {
        return translate(messageCode.toString());
    }

}
