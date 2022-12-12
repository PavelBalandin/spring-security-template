package com.example.service;

import com.example.domain.User;
import com.example.dto.RegistrationRequest;
import com.example.dto.UserDTO;

import java.util.Set;

public interface UserService {

    User findByUsername(String username);

    UserDTO create(RegistrationRequest registrationRequest);

    Set<UserDTO> getAll();

    UserDTO update(Long id, UserDTO userDTO);

    void delete(Long id);

}
