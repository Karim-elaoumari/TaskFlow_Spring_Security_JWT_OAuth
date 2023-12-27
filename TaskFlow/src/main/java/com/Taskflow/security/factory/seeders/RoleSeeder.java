package com.Taskflow.security.factory.seeders;

import com.Taskflow.security.models.entities.PrivilegeEntity;
import com.Taskflow.security.models.entities.Role;
import com.Taskflow.security.repositories.PrivilegeRepository;
import com.Taskflow.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final com.Taskflow.security.factory.fakers.RoleFaker RoleFaker;

    public void seed() {
        List<PrivilegeEntity> privileges = privilegeRepository.findAll();
        privileges.remove(4);
        Role role = RoleFaker.makeRole(privileges,"ADMIN");
        roleRepository.save(role);
        Role role1 = RoleFaker.makeRole(privileges,"USER");
        roleRepository.save(role1);
    }

}
