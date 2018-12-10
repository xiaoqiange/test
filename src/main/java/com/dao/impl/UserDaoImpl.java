package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseDao;
import com.dao.UserDao;
import com.pojo.User;
import com.vo.ZTree;

@Repository(value = "userDao")
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public int addUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<User> getUserList() {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer();
        sql.append("select * from user");
        return this.findList(sql.toString(), User.class);
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
    public User selectById(int id) {
        // TODO Auto-generated method stub
        User user = this.getHibernateTemplate().get(User.class, id);
        return user;
    }

    @Override
    public User loginUser(User user) {
        StringBuffer sql=new StringBuffer();
        sql.append("select * from user where name=? and password=?");
        System.out.println("dao ------------>"+user.getName());
        System.out.println("dao ------------>"+user.getPassword());
        User us=this.findForObject(sql.toString(), new Object [] {user.getName(),user.getPassword()}, User.class);
        return us;
    }

//    @Override
//    public List<ZTree> userSource(User user) {
//        StringBuffer sql=new StringBuffer();
//        
//        return null;
//    }

}
