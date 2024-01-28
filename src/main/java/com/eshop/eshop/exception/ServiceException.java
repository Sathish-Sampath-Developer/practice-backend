package com.eshop.eshop.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ServiceException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServiceException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
