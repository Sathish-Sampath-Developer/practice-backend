package com.eshop.eshop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SuccessResponse {
    private boolean success;
    private String message;
    private String token;

    public SuccessResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SuccessResponse(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }
}
