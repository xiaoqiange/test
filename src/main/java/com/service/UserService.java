package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.User;

public interface UserService {

    public int addUser(User user);

    public List<User> getUserList();

    public User selectById(int id);

    public int delete(Integer stu_id);

    public int update(User user);

    public User loginUser(User user);
}
