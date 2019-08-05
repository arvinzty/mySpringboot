package com.how2java.tmall.service;

import com.how2java.tmall.dao.ProductImageDao;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Productimage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    ProductImageDao productImageDao;
    @Autowired
    ProductService productService;
    public static final String type_single="single";
    public static final String type_detail="detail";

    public void add(Productimage productimage){
        productImageDao.save(productimage);
    }
    public void delete(int id){
        productImageDao.delete(id);
    }
    public Productimage get(int id){
        return productImageDao.findOne(id);
    }
    public List<Productimage> listSingleProductImages(Product product){
        return productImageDao.findByProductAndTypeOrderByIdDesc(product, ProductImageService.type_single);
    }
    public List<Productimage> listDetailProductImages(Product product){
        return productImageDao.findByProductAndTypeOrderByIdDesc(product,ProductImageService.type_detail);
    }
    public void setFirstProdutImage(Product produt){
        List<Productimage> productimages=listSingleProductImages(produt);
        if(!productimages.isEmpty()){
            produt.setFirstProductImage(productimages.get(0));
        }else {
            produt.setFirstProductImage(new Productimage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。
        }
    }

    public void setFirstProdutImage(List<Product> produts){
        for (Product tem:produts){
           setFirstProdutImage(tem);
        }
    }
}
