package com.example.controller;

import com.example.dto.AuthenticationRequest;
import com.example.dto.RegistrationRequest;
import com.example.dto.UserDTO;
import com.example.security.JWTUtils;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public ResponseEntity<Map<String, Object>> auth(@RequestBody AuthenticationRequest request) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
      );
    //TODO
      UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
      Map<String, Object> response = new HashMap<>();
      if(userDetails != null){
          String token = jwtUtils.generateToken(userDetails);
          response.put("token", token);
      }else {
          response.put("message", "Some error gone wrong");
      }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
