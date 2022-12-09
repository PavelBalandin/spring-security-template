package com.example.mapper;

import com.example.domain.Role;
import com.example.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleMapper {

    private final ModelMapper mapper;

    public RoleDTO toDTO(Role role) {
        return mapper.map(role, RoleDTO.class);
    }

    public Role toEntity(RoleDTO roleDTO) {
        return mapper.map(roleDTO, Role.class);
    }

}
