package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pojo.User;
import com.service.UserService;
import com.service.UserSourceService;
import com.util.ISearchCondition;
import com.vo.UserSource;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserSourceService sourceService;
    
    @RequestMapping("/userlist")
    @ResponseBody
    public Object getUsertList() {
        List<User> list=userService.getUserList();
        return this.setResultData(Long.valueOf(list.size()), list);
    }
    
    @RequestMapping("/userPage")
    public String test() {
        return "userList";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping(value="/source",produces="application/json;charset=utf-8")
    @ResponseBody
    public Object sourceTree(){
        ISearchCondition isc=getSearchCondition();
        User user=isc.getCurUser();
        List<UserSource> list=sourceService.getUserSources(user);
        String json=JSON.toJSONString(list);
        return list;
    }
    
}
