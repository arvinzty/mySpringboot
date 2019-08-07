package com.how2java.tmall.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;

@Controller
public class ForePageController {
    @GetMapping(value="/")
    public String index(){
        return "redirect:home";
    }
    @GetMapping(value="/home")
    public String home(){
        return "fore/login";
    }
    @GetMapping(value="/register")
    public String register(){
        return "fore/register";
    }
    @GetMapping(value="/registerSuccess")
    public String Page(){
        return "fore/registerSuccess";
    }
//   登出
    @GetMapping(value = "/loginOut")
    public String loginOut(){
//        session.removeAttribute("user");
        Subject subject=SecurityUtils.getSubject();
        if (subject.isAuthenticated())
            subject.logout();
        return "redirect:home";
    }


}
