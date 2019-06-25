package com.vo;

import java.util.List;

import com.pojo.Role;
import com.pojo.User;

public class UserVo extends User{
    /**
    * @Fields serialVersionUID :
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
