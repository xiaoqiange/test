package com.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri=request.getRequestURI();
        String contextPath=request.getContextPath();
        String url=requestUri.substring(contextPath.length());
//        System.out.println("requestUri-------------------->"+requestUri);
//        System.out.println("contextPath-------------------->"+contextPath);
//        System.out.println("url-------------------->"+url);
        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return false;
        }
        return true;
    }
}
