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
    
    public int addUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<User> getUserList() {
        // TODO Auto-generated method stub
        return userdao.getUserList();
    }

    public int delete(Integer uid) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int update(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Transactional
    public User selectById(int id) {
        // TODO Auto-generated method stub
        return userdao.selectById(id);
    }

    public User loginUser(User user) {
        return userdao.loginUser(user);
    }

}
