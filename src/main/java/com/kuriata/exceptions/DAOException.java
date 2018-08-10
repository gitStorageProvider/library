package com.kuriata.exceptions;

public class DAOException extends Exception {
    public DAOException(String reason, Throwable cause){
        super(reason, cause);
    }
}

