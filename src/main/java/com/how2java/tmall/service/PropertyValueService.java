package com.how2java.tmall.service;

import com.how2java.tmall.dao.PropertyDao;
import com.how2java.tmall.dao.PropertyValueDao;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Property;

import com.how2java.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class PropertyValueService {
    @Autowired
    PropertyValueDao propertyValueDao;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyDao property;
    public void update(PropertyValue propertyValue){
        propertyValueDao.save(propertyValue);
    }
//    给ropertyValue赋值，第一次的时候初始化
    public void init(Product product){
        Category category=categoryService.getOne(product.getCategory().getId());
        List<Property> properties=property.findByCategory(category);
        for (Property tem:properties){
            if (null==propertyValueDao.findByProductAndProperty(product,tem)){
                PropertyValue propertyValue=new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(tem);
                propertyValueDao.save(propertyValue);
            }
        }
    }

    public List<PropertyValue> list(Product product){
        return propertyValueDao.findByProductOrderByIdDesc(product);
    }
}
