package com.example.service;

import com.example.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();

    public void addUser(User user);

    public void delUser(User user);

    public void updateUser(User user);

    public User findUserById(User user);
}
