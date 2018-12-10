package com.dao;

import java.util.List;

import com.pojo.User;
import com.vo.ZTree;

public interface UserDao {

    public int addUser(User user);

    public User selectById(int id);

    public List<User> getUserList();

    public int delete(Integer uid);

    public int update(User user);
    
    public User loginUser(User user);
    
//    public List<ZTree> userSource(User user);
}
