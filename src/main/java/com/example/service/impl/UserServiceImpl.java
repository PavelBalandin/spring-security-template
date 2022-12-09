package com.example.service.impl;

import com.example.domain.User;
import com.example.dto.RegistrationRequest;
import com.example.dto.UserDTO;
import com.example.exception.ResourcesAlreadyExistsException;
import com.example.exception.UserNotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.RoleService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDTO create(RegistrationRequest registrationRequest) {
        User user = userMapper.registrationRequestToEntity(registrationRequest);
        user.setRoles(Set.of(roleService.getByName("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResourcesAlreadyExistsException("User with the same username already exists");
        }

        User saved = userRepository.save(user);
        return userMapper.toDTO(saved);

    }


}
