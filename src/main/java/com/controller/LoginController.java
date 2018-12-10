package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pojo.User;
import com.service.RoleService;
import com.service.UserService;
import com.util.ISearchCondition;
import com.vo.RoleVo;
import com.vo.ZTree;


@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
    
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }
    
    @RequestMapping(value="/login")
    public String login(){
        ISearchCondition isc=getSearchCondition();
        String userName=isc.getValue("name");
        String password=isc.getValue("password");
        System.out.println(userName);
        System.out.println(password);
        User user=new User();
        user.setName(userName);
        user.setPassword(password);
        User us=userService.loginUser(user);
        if(us!=null){
            this.getSession().setAttribute("user", us);
        }
        return "index";
    }
    /**
     * 
     * Description:[easyui  tree]<br>
     * @author liufq
     * @update 2018年6月8日
     * @return
     */
    @RequestMapping("/tree")
    @ResponseBody
    public Object tree(){
        List<RoleVo> list=roleService.selectAllRole();
        String json = JSON.toJSONString(list, true);
        return json;
    }
    

    @RequestMapping("/myTree")
    public String myTree(Model model) {
        return "myTree";
    }
    
    /**
     * 
     * Description:[ztree]<br>
     * 处理逻辑：[业务复杂的方法罗列出处理逻辑，可选]<br>
     * 适用场景：[描述方法使用的业务场景，可选]<br>
     * @author liufq
     * @update 2018年6月8日
     * @return
     */
    @RequestMapping(value="/ztree",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object ztree(){
        List<ZTree> list=roleService.selectZtree();
        String json = JSON.toJSONString(list, true);
        return json;
    }
}
