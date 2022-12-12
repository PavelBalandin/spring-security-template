package com.example.controller;

import com.example.dto.AuthenticationRequest;
import com.example.dto.RegistrationRequest;
import com.example.dto.UserDTO;
import com.example.security.JWTUtils;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JWTUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<>(userService.create(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String accessToken = jwtUtils.generateToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);

        Map<String, String> response = Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
