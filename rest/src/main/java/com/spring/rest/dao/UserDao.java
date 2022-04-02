package com.spring.rest.dao;

import com.spring.rest.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void updateUser(User user);

    void removeUserById(Long id);

    User getUserByUsername(String username);

    User getUserById(Long id);

    List<User> getAllUsers();
}
