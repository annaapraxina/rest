package com.spring.rest.dao;

import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user, Set<Role> roles) {
        user.setRoles(roles);
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user, Long id, Set<Role> roles) {
        user.setId(id);
        user.setRoles(roles);
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> typedQuery
                = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username", User.class);
        typedQuery.setParameter("username", username);
        return typedQuery.getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
