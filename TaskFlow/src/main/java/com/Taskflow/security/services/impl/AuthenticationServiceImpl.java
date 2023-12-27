package com.Taskflow.security.services.impl;

import com.Taskflow.app.exceptionHandler.ResourceNotFoundException;
import com.Taskflow.security.models.dto.UserLoginRequestDTO;
import com.Taskflow.security.models.dto.UserResponseDTO;
import com.Taskflow.security.models.entities.RefreshToken;
import com.Taskflow.security.models.entities.User;
import com.Taskflow.security.repositories.UserRepository;
import com.Taskflow.security.services.JwtService;
import com.Taskflow.security.services.AuthenticationService;
import com.Taskflow.security.services.RefreshTokenService;
import com.Taskflow.security.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;

    @Override
    public UserResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           userLoginRequestDTO.getEmail(),
                           userLoginRequestDTO.getPassword()
                   )
           );
         }catch (Exception e){
              throw new BadCredentialsException(e.getMessage());
         }
        User user = userRepository.findByEmail(userLoginRequestDTO.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("Email Not found "));
        String jwtToken  = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return UserResponseDTO.fromUser(user,jwtToken,refreshToken.getToken());

    }
    @Override
    public UserResponseDTO register(User user) {
        user.setRole(roleService.getRoleByName("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user =  userRepository.save(user);
        String jwtToken  = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return UserResponseDTO.fromUser(user,jwtToken,refreshToken.getToken());
    }
    @Override
    public void  logout(String token){
       refreshTokenService.revokeRefreshToken(token);
    }
}
