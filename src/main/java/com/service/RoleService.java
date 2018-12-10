package com.service;

import java.util.List;

import com.pojo.User;
import com.vo.RoleVo;
import com.vo.ZTree;

public interface RoleService {

    public List<RoleVo> selectAllRole();

    public List<ZTree> selectZtree();
    
//    public List<ZTree> selectUserSource(User user);
} 
