package com.example.service.impl;

import com.example.domain.Role;
import com.example.dto.RoleDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.ResourcesAlreadyExistsException;
import com.example.mapper.RoleMapper;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Set<RoleDTO> getAll() {
       return roleRepository.findAll().stream().map(roleMapper::toDTO).collect(Collectors.toSet());
    }


    @Override
    public RoleDTO create(RoleDTO roleDTO) {
        Optional<Role> optional = roleRepository.findByName(roleDTO.getName());

        if(optional.isPresent()){
            throw new ResourcesAlreadyExistsException();
        }

        Role role = roleRepository.save(roleMapper.toEntity(roleDTO));
        return roleMapper.toDTO(role);
    }

    @Override
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        Role roleFromDB = roleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        BeanUtils.copyProperties(roleMapper.toEntity(roleDTO), roleFromDB, "id");
        Role role = roleRepository.save(roleFromDB);
        return roleMapper.toDTO(role);
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        roleRepository.delete(role);
    }


}
