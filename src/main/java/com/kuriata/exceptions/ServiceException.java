package com.kuriata.exceptions;

public class ServiceException extends Exception {
    public ServiceException(String reason, Throwable cause) {
        super(reason, cause);
    }
}