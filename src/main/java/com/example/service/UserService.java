package com.example.service;

import com.example.domain.User;
import com.example.dto.RegistrationRequest;
import com.example.dto.UserDTO;

public interface UserService {

    User findByUsername(String username);

    UserDTO create(RegistrationRequest registrationRequest);

}
