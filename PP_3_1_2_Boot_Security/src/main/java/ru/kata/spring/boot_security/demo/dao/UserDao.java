package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void removeUserById(long id);

    void updateUser(User user);

}
