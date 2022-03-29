package com.spring.rest.service;

import com.spring.rest.dao.RoleDao;
import com.spring.rest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void removeRoleById(Long id) {
        roleDao.removeRoleById(id);
    }

    @Override
    public Role getRoleByUsername(String role) {
        return roleDao.getRoleByUsername(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Set<Role> getRoleById(List<Long> rolesId) { return roleDao.getRoleById(rolesId); }
}
