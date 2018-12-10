package com.vo;

import java.util.ArrayList;
import java.util.List;

import com.pojo.Role;

public class ZTree extends Role {

    private String isParent;

    private String open;

    private String name;

    private Long uid;
    
    private String url;

    private List<ZTree> children = new ArrayList<ZTree>();

    public void addOption(ZTree ztree) {
        children.add(ztree);
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ZTree> getChildren() {
        if (children.size() == 0) {
            return null;
        }
        return children;
    }

    public void setChildren(List<ZTree> children) {
        this.children = children;
    }

}
