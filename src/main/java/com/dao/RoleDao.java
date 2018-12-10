package com.dao;

import java.util.List;

import com.pojo.Role;
import com.pojo.User;
import com.vo.RoleVo;
import com.vo.ZTree;

public interface RoleDao {

    public int addRole(Role role);

    public List<RoleVo> selectAllRole();

    public List<ZTree> selectZtree();

//    public List<ZTree> selectUserSource(User user);
}
