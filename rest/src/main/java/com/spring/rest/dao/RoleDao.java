package com.spring.rest.dao;

import com.spring.rest.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    void addRole(Role role);

    void updateRole(Role role);

    void removeRoleById(Long id);

    Role getRoleByUsername(String role);

    List<Role> getAllRoles();

    Set<Role> getRoleById(List<Long> rolesId);
}
