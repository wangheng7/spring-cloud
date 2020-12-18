package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserMapper userMapper;

    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }

    public void addUser(User user){
        userMapper.addUser(user);
    }

    public void delUser(User user){
        userMapper.delUser(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public User findUserById(User user){
        return userMapper.findUserById(user);
    }
}
