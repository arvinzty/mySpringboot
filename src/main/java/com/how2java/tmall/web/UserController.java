package com.how2java.tmall.web;

import com.how2java.tmall.dao.UserDao;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.UserService;
import com.how2java.tmall.util.Page4Navigator;
import com.how2java.tmall.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page4Navigator<User> list(@RequestParam(value = "start",defaultValue = "0")int start, @RequestParam(value = "size",defaultValue = "5")int size){
        start=start<0?0:start;
        return userService.list(start,size,5);
    }

    @PostMapping("/foreregister")
    public Object add(@RequestBody User user){
        String name= HtmlUtils.htmlEscape(user.getName());
        String password=user.getPassword();
        if (userService.isExist(name)){
            return Result.fail("账号已经注册");
        }
        String salt=new SecureRandomNumberGenerator().nextBytes().toString();
        int time=3;
        String algorithmName="md5";
        password=new SimpleHash(algorithmName,password,salt,time).toString();
        user.setSalt(salt);
        user.setPassword(password);
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/forelogin")
    public Object login(@RequestBody User user){
        String name=HtmlUtils.htmlEscape(user.getName());
        String password=user.getPassword();
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        try {
            subject.login(token);
            subject.getSession().setAttribute("user",user);
            return Result.success();
        }catch (Exception e){
            return Result.fail("账号密码错误");
        }
    }


}
