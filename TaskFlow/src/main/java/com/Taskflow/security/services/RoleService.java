package com.Taskflow.security.services;

import com.Taskflow.security.models.entities.Role;

public interface RoleService {

  Role getRoleByName(String name);

}
