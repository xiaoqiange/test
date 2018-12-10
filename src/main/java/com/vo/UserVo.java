package com.vo;

import java.util.List;

import com.pojo.Role;
import com.pojo.User;

public class UserVo extends User{
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = 2375656372587618649L;
    
    private String rname;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
    
}
