package com.eshop.eshop.service.impl;

import com.eshop.eshop.Utils.CreatePasswordResetToken;
import com.eshop.eshop.dto.*;
import com.eshop.eshop.entity.RoleEntity;
import com.eshop.eshop.entity.UserEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.RoleRepository;
import com.eshop.eshop.repository.UserRepository;
import com.eshop.eshop.service.AuthService;
import com.eshop.eshop.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CreatePasswordResetToken createPasswordResetToken;

    @Override
    public SuccessResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getPhoneOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.generateToken(authentication);

        return new SuccessResponse(true, "Your are login was successful!", token);
    }

    @Override
    public SuccessResponse register(RegisterDto registerDto) {

        if (userRepository.existsByPhone(registerDto.getPhone())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Phone is already exists!.");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        UserEntity user = new UserEntity();

        user.setPhone(registerDto.getPhone());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        if (user.getRoles().isEmpty()) {
            RoleEntity defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found!"));
            user.setRoles(Collections.singleton(defaultRole));
        }

        userRepository.save(user);

        return new SuccessResponse(true, "Your account signup was successful!.");
    }

    @Override
    public SuccessResponse forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        UserEntity user = userRepository
                .findByPhoneOrEmail(forgotPasswordDto.getPhoneOrEmail(), forgotPasswordDto.getPhoneOrEmail())
                .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Email or Phone is incorrect!."));

        String passwordResetToken = createPasswordResetToken.generatePasswordResetToken();

        user.setPasswordResetToken(createPasswordResetToken.hashToken(passwordResetToken));
        user.setPasswordResetTokenExpiresIn(LocalDateTime.now().plusMinutes(10));

        userRepository.save(user);

        return new SuccessResponse(true, "Password reset token issued successfully!", passwordResetToken);
    }

    @Override
    public SuccessResponse resetPassword(String passwordResetToken, ResetPasswordDto resetPasswordDto) {
        UserEntity user = userRepository.findByPasswordResetTokenAndPasswordResetTokenExpiresInAfter(createPasswordResetToken.hashToken(passwordResetToken), LocalDateTime.now()).orElseThrow(()->new ServiceException(HttpStatus.NOT_FOUND,"Invalid or expired password reset token!."));

        user.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        user.setPasswordResetToken(null);
        user.setPasswordResetToken(null);

        userRepository.save(user);

        return new SuccessResponse(true,"Your password was updated successfully!");
    }

}
