package com.test.common;

public class DlebException extends RuntimeException {

    public DlebException(String message) {
        super(message);
    }

    public DlebException(String message, Throwable cause) {
        super(message, cause);
    }
}
