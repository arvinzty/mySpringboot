package com.how2java.tmall.service;

import com.how2java.tmall.dao.UserDao;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public Page4Navigator<User> list(int start, int size, int navigatePage){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page<User> jpa=userDao.findAll(pageable);
        return new  Page4Navigator(jpa,navigatePage);
    }
    public User get(String username){
        return userDao.findByName(username);
    }
    public Boolean isExist(String name){
        User user=userDao.findByName(name);
        if (null==user)
            return false;
        return true;
    }
    public void add(User user){
        userDao.save(user);
    }
}
