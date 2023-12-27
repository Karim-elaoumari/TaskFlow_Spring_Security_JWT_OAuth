package com.Taskflow.security.factory.fakers;

import com.Taskflow.security.models.entities.PrivilegeEntity;
import com.Taskflow.security.models.entities.Role;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleFaker {
    private final Faker faker = new Faker();

    public Role makeRole(List<PrivilegeEntity> privileges, String name) {
        return Role.builder()
                .name(name)
                .privileges(
                      privileges
                )
                .build();
    }


}
