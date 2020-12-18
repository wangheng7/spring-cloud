package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAllUser();

    public void addUser(User user);

    public void delUser(User user);

    public void updateUser(User user);

    public User findUserById(User user);
}
