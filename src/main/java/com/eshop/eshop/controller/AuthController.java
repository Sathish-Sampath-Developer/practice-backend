package com.eshop.eshop.controller;

import com.eshop.eshop.dto.*;
import com.eshop.eshop.dto.auth.*;
import com.eshop.eshop.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth Controller", description = "This url for Authenticate Users.")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signUp(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.ACCEPTED);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<SuccessResponse> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        return new ResponseEntity<>(authService.forgotPassword(forgotPasswordDto), HttpStatus.OK);
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<SuccessResponse> forgotPassword(@PathVariable(name = "token") String passwordResetToken, @RequestBody ResetPasswordDto resetPasswordDto) {
        return new ResponseEntity<>(authService.resetPassword(passwordResetToken,resetPasswordDto), HttpStatus.OK);
    }
}
