package com.kuriata.exceptions;

public class ServletException extends Exception {
    public ServletException(String reason, Throwable cause) {
        super(reason, cause);
    }
}