package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    //
    @RequestMapping("/addUser")
    public void addUser(User user){
        userService.addUser(user);
    }

    @RequestMapping("/delUser")
    public void delUser(User user){
        userService.delUser(user);
    }

    @RequestMapping("/updateUser")
    public void updateUser(User user){
        userService.updateUser(user);
    }

    @RequestMapping("/findUserById")
    public User findUserById(User user){
        return userService.findUserById(user);
    }
}
