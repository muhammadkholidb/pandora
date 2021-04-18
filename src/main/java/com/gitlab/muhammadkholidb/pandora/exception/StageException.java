package com.gitlab.muhammadkholidb.pandora.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StageException extends RuntimeException {
    
    private String message;
}
