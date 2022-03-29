package com.spring.rest.dao;


import com.spring.rest.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRoleById(Long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public Role getRoleByUsername(String role) {
        TypedQuery<Role> queryRole = entityManager.createQuery("select r from Role r where r.role=:role",
                Role.class).setParameter("role", role);
        return queryRole.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Set<Role> getRoleById(List<Long> rolesId) {
        TypedQuery<Role> role = entityManager.createQuery("select r from Role r where r.id in :role", Role.class)
                .setParameter("role", rolesId);
        return new HashSet<>(role.getResultList());
    }
}
