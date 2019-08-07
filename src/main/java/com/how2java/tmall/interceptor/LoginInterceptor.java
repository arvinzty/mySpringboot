package com.how2java.tmall.interceptor;


import org.apache.commons.lang.StringUtils;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
//当没有登录时，无法访问订单页面
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Subject subject= SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            System.out.println("/执行1");
            return true;
        }
//        if (null==httpServletRequest.getSession().getAttribute("user")){
//            System.out.println("/执行2");
//            return true;
//        }
//        注册的几个访问路径
        String[] ss={"/register","/home","/forelogin","/foreregister","/registerSuccess","/loginOut"};
        String contenxPath=httpServletRequest.getContextPath();
        String uri=httpServletRequest.getRequestURI();
        String s=StringUtils.remove(uri,contenxPath);
        if (!beginWith(s,ss)){
            httpServletResponse.sendRedirect("home");
//            System.out.println("进行判断");
            return false;
        }
//        System.out.println("方法前执行");
        return true;
    }



    public boolean beginWith(String s,String[] ss){
        for (String tem:ss){

            if ( StringUtils.startsWith(s,tem))
                return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("方法中执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("方法后执行");
    }
}
