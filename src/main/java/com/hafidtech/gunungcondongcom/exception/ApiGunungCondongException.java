package com.hafidtech.gunungcondongcom.exception;

import org.springframework.http.HttpStatus;

public class ApiGunungCondongException extends RuntimeException{

    private static final long serialVersionUID = -6593330219878485669L;

    private final HttpStatus status;
    private final String message;

    public ApiGunungCondongException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiGunungCondongException(HttpStatus status, String message, Throwable exception) {
        super(exception);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


}
