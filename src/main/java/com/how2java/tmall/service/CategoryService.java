package com.how2java.tmall.service;

import com.how2java.tmall.dao.CategoryDao;
import com.how2java.tmall.pojo.Category;

import com.how2java.tmall.util.Page4Navigator;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "categorise")
public class CategoryService<page4Navigator> {
    @Autowired
    CategoryDao categoryDao;

    //倒叙查询所有数据
//    缓存的key查看缓存
    @Cacheable(key="'categories-all'")
    public List<Category> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return categoryDao.findAll(sort);
    }
//    @Cacheable(key="'categories-page-'+#p0+'-'+#p1")
    public Page4Navigator<Category> list(int start,int size,int navigatePages){
//分页查询，start查询起始位置，size单页显示数据，navigatePages
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page pageFromJpa=categoryDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJpa,navigatePages);
    }
    @CacheEvict(allEntries = true)
//    @CachePut(key = "'category-one-'+#p0")
    public void add(Category category){
        categoryDao.save(category);
    }

    @CacheEvict(allEntries = true)
//    @CacheEvict(key = "'categories-one-'+#p0")
    public void delete(int id){
        categoryDao.delete(id);
    }
    @Cacheable(key="'categories-one-'+#p0")
    public Category getOne(int id){
        return categoryDao.findOne(id);
    }
    @CacheEvict(allEntries = true)
//    @CachePut(key = "'categories-one-'+#p0")
    public void updata(Category category){
        categoryDao.save(category);
    }
}
