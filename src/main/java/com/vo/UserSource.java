package com.vo;

import java.util.ArrayList;
import java.util.List;

public class UserSource {

    private String name;

    private String rname;

    private Integer sid;

    private String sname;

    private Integer supersid;

    private String url;

    private List<UserSource> children = new ArrayList<UserSource>();;

    public void addOptions(UserSource userSource) {
        children.add(userSource);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url =url;
    }

    public Integer getSupersid() {
        return supersid;
    }

    public void setSupersid(Integer supersid) {
        this.supersid = supersid;
    }

    public List<UserSource> getChildren() {
        if(supersid==null)
            return children;
        if(children.size()==0)
            return null;
        return children;
    }

    public void setChildren(List<UserSource> children) {
        this.children = children;
    }

}
