package com.how2java.tmall.service;

import com.how2java.tmall.dao.CategoryDao;
import com.how2java.tmall.dao.PropertyDao;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PropertyService {
    @Autowired
    PropertyDao propertyDao;
    @Autowired
    CategoryDao categoryDao;
    public void add(Property property){
        propertyDao.save(property);
    }
    public void delete(int id){
        propertyDao.delete(id);
    }
    public void updata(Property property){
        propertyDao.save(property);
    }

    public Property getOne(int id){
        return propertyDao.findOne(id);
    }


    public Page4Navigator<Property> list(int cid,int start,int size,int navigatePages){
        Category category=categoryDao.findOne(cid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable page=new PageRequest(start,size,sort);
        Page<Property> page1=propertyDao.findByCategory(category,page);
        return new Page4Navigator<Property>(page1,navigatePages);
    }

}
