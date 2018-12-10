package com.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.pojo.User;
import com.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userdao;
    
    @Override
    public int addUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<User> getUserList() {
        // TODO Auto-generated method stub
        return userdao.getUserList();
    }

    @Override
    public int delete(Integer uid) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @Transactional
    public User selectById(int id) {
        // TODO Auto-generated method stub
        return userdao.selectById(id);
    }

    @Override
    public User loginUser(User user) {
        return userdao.loginUser(user);
    }

}
