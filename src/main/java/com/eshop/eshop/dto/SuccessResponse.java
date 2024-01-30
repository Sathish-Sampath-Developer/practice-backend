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
    public SuccessResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
