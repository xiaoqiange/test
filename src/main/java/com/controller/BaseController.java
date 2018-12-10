package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.pojo.User;
import com.util.ISearchCondition;
import com.util.SearchCondition;

public class BaseController {
    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    @ModelAttribute
    public void setRepOrReq(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            this.request = req;
            this.response = resp;
            this.session = req.getSession();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public synchronized ISearchCondition getSearchCondition(){
        ISearchCondition isc = new SearchCondition(this.request);
        isc.setCurUser(getLoginUser());
        return isc;
    }

    public User getLoginUser() {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return user;
        }
        return null;
    }
    
    protected Map<String, Object> setResultData(Long total, Object rows) {
        Map<String, Object> resultMsg = new HashMap<String, Object>();
        resultMsg.put("total", total);
        resultMsg.put("rows", rows);
        return resultMsg;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
