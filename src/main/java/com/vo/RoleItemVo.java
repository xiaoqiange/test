package com.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleItemVo {
    private List<RoleVo> itemVo=new ArrayList<RoleVo>();

    public void addRoleVo(RoleVo roleVo){
        itemVo.add(roleVo);
    }

    public List<RoleVo> getItemVo() {
        return itemVo;
    }

    public void setItemVo(List<RoleVo> itemVo) {
        this.itemVo = itemVo;
    }
        
}
