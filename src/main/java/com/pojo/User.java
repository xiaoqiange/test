package com.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    private String name;
    private String password;
    private String code;
    private String phone;
    private Integer sex;
    private Integer age;
    private Date birthday;
    @Transient
    private final SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
    public User() {
        // TODO Auto-generated constructor stub
    }
    
    public User(String name, String password, String code, String phone, Integer sex, Integer age, Date birthday) {
        super();
        this.name = name;
        this.password = password;
        this.code = code;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
    }

    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSex1() {
        if(sex==1)
            return "男";
        return "女";
    }
    public Integer getSex(){
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getBir() {
        String bir=sd.format(birthday);
        return bir;
    }
    public Date getBirthday() {
        return birthday;
    }
}
