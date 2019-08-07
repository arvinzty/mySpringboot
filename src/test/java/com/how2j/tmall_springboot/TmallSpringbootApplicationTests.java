package com.how2j.tmall_springboot;

import com.how2java.tmall.Application;
import com.how2java.tmall.dao.CategoryDao;
import com.how2java.tmall.dao.UserDao;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.UserService;
import com.how2java.tmall.util.Result;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;


import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.HtmlUtils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TmallSpringbootApplicationTests {

	@Autowired
	CategoryDao service;
//	@Autowired
//	UserDao userDao;
	@Autowired
	UserService userService;
	@Test
	public void test() {
		Iterator iterator=service.findAll().iterator();
		while (iterator.hasNext()){
			Category category= (Category) iterator.next();
			System.out.println("category:name="+category.getName());
		}
	}
//	@Test
//	public void test1(){
//		for (int i=1;i<13;i++) {
//			User tem=new User();
//			tem.setName("测试账号第"+i+"号");
//			tem.setPassword("asd");
//			tem.setSalt(i+"asd");
//			userDao.save(tem);
//		}
//		System.out.println("执行成功");
//	}
	@Before
	public void before() {
		System.out.println("测试开始");
	}
	@Test
	public void test2(){
		User user=new User();
		user.setName("arvinzty");
		user.setPassword("123");
		String name=user.getName();
		String password=user.getPassword();
		name= HtmlUtils.htmlEscape(name);
		user.setName(name);
		if (userService.isExist(user.getName())){
			System.out.println("用户名已经被使用");
		}else {
			String salt = new SecureRandomNumberGenerator().nextBytes().toString();
			int times = 3;
			String algorithmName = "md5";
			String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
			user.setSalt(salt);
			user.setPassword(encodedPassword);
			userService.add(user);
			System.out.println("注册成功");
		}
	}

	@Test
	public void test3(){
		User user=new User();
		user.setName(HtmlUtils.htmlEscape("arvinzty"));
		user.setPassword("123");
		Subject subject= SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getName(),user.getPassword());
		try {
			subject.login(token);
//			User u=userService.get(user.getName());
			System.out.println("登录成功");
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("登录错误");
		}

	}

}


