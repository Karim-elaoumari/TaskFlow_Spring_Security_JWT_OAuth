package com.Taskflow.security.services.impl;

import com.Taskflow.security.models.entities.Role;
import com.Taskflow.security.repositories.RoleRepository;
import com.Taskflow.security.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()-> new RuntimeException("Role not found"));
    }
}
