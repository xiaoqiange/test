package com.vo;

import com.pojo.Role;

public class RoleVo extends Role {
    private String superName;
    private RoleItemVo itemVo=new RoleItemVo();
    
    public void addOption(RoleVo roleVo){
        itemVo.addRoleVo(roleVo);
    }
    
    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public RoleItemVo getItemVo() {
        return itemVo;
    }

    public void setItemVo(RoleItemVo itemVo) {
        this.itemVo = itemVo;
    }
    
}
