package com.example.mapper;

import com.example.domain.User;
import com.example.dto.RegistrationRequest;
import com.example.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UserDTO toDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    public User registrationRequestToEntity(RegistrationRequest registrationRequest) {
        return mapper.map(registrationRequest, User.class);
    }

}
