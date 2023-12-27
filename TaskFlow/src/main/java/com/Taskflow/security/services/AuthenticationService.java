package com.Taskflow.security.services;

import com.Taskflow.security.models.dto.UserLoginRequestDTO;
import com.Taskflow.security.models.dto.UserResponseDTO;
import com.Taskflow.security.models.entities.User;

public interface AuthenticationService {
    UserResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);
    UserResponseDTO register(User user);
    void  logout(String token);
}
