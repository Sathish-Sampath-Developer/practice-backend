package com.eshop.eshop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private Date timeStamp;
    private String message;
    private String details;
}
