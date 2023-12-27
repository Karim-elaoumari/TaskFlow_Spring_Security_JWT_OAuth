package com.Taskflow.security.controllers;

import com.Taskflow.security.SecurityExceptionsHandlers.exception.handlers.response.ResponseMessage;
import com.Taskflow.security.models.dto.RefreshhTokenRequestDTO;
import com.Taskflow.security.models.dto.UserLoginRequestDTO;
import com.Taskflow.security.models.dto.UserRegisterRequestDTO;
import com.Taskflow.security.models.dto.UserResponseDTO;
import com.Taskflow.security.services.AuthenticationService;
import com.Taskflow.security.services.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginRequestDTO loginRequest) {
        UserResponseDTO userDTO = authenticationService.login(loginRequest);
        return ResponseMessage.ok(userDTO,"you have authenticated ");
    }
    @PostMapping("/register")
    public ResponseEntity register(@Valid  @RequestBody UserRegisterRequestDTO registerRequest) {
        UserResponseDTO userDTO = authenticationService.register(registerRequest.toUser());
        return ResponseMessage.ok(userDTO,"you have registred");
    }
    @PostMapping("/refresh-token")
    public ResponseEntity refreshToken(@Valid @RequestBody RefreshhTokenRequestDTO request) {
        return ResponseMessage.ok(refreshTokenService.generateNewToken(request.getRefreshToken()),"you have refreshed your token");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(@Valid @RequestBody RefreshhTokenRequestDTO request) {
        refreshTokenService.revokeRefreshToken(request.getRefreshToken());
        return ResponseMessage.ok(null,"you have logged out");
    }
}
