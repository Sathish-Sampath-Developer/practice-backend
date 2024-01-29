package com.eshop.eshop.service;


import com.eshop.eshop.dto.*;

public interface AuthService {

    JwtResponse login(LoginDto loginDto);

    SuccessResponse register(RegisterDto registerDto);

    SuccessResponse forgotPassword(ForgotPasswordDto forgotPasswordDto);

    SuccessResponse resetPassword(String passwordResetToken, ResetPasswordDto resetPasswordDto);

    String getAuthenticatedUsername();
}
