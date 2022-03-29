package com.spring.rest.dao;

import com.spring.rest.model.Role;
import com.spring.rest.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    void addUser(User user, Set<Role> roles);

    void updateUser(User user, Long id, Set<Role> roles);

    void removeUserById(Long id);

    User getUserByUsername(String username);

    User getUserById(Long id);

    List<User> getAllUsers();
}
