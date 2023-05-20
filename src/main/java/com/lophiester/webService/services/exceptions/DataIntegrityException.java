package com.lophiester.webService.services.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;


public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String message) {
        super(message);
    }
    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
