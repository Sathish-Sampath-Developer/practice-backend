package com.eshop.eshop.service;


import com.eshop.eshop.dto.*;

public interface AuthService {

    SuccessResponse login(LoginDto loginDto);

    SuccessResponse register(RegisterDto registerDto);

    SuccessResponse forgotPassword(ForgotPasswordDto forgotPasswordDto);

    SuccessResponse resetPassword(String passwordResetToken, ResetPasswordDto resetPasswordDto);
}
