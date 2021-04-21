package com.gitlab.muhammadkholidb.pandora.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class StageException extends RuntimeException {
    
    private final String message;
}
