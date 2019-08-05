package com.how2java.tmall.service;

import com.how2java.tmall.dao.CategoryDao;
import com.how2java.tmall.dao.ProductDao;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;

    public void add(Product product){
        productDao.save(product);
    }
    public void update(Product product){
        productDao.save(product);
    }
    public void delete(int id){
        productDao.delete(id);
    }
    public Page4Navigator<Product> list(int cid,int start,int size){
        Category category=categoryDao.findOne(cid);
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable page=  new PageRequest(start,size,sort);
        Page<Product> page1=productDao.findAllByCategory(category,page);
        return new Page4Navigator<Product>(page1,5);
    }
    public Product getOne(int id){
        return productDao.findOne(id);
    }
}
