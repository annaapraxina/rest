package com.spring.rest.service;

import com.spring.rest.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);

    void updateRole(Role role);

    void removeRoleById(Long id);

    Role getRoleByUsername(String username);

    List<Role> getAllRoles();

    Role getRoleById(long id);
}
