package com.example.service;

import com.example.domain.Role;
import com.example.dto.RoleDTO;

import java.util.Set;

public interface RoleService {

    Role getByName(String name);

    Set<RoleDTO> getAll();

    RoleDTO create(RoleDTO roleDTO);

    RoleDTO update(Long id, RoleDTO roleDTO);

    void delete(Long id);

}
