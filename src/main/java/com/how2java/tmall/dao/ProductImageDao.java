package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDao extends JpaRepository<Productimage,Integer> {
    public List<Productimage> findByProductAndTypeOrderByIdDesc(Product product,String type);
}
