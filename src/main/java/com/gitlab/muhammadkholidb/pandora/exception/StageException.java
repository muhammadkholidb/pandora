package com.gitlab.muhammadkholidb.pandora.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StageException extends RuntimeException {

    public StageException(String message, Throwable cause) {
        super(message, cause);
    }
}
