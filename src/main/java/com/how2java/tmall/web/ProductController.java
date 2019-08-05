package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Productimage;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService p;
    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid")int cid, @RequestParam(value = "start",defaultValue = "0")int start, @RequestParam(value = "size",defaultValue = "5")int size){
        start=start<0?0:start;
        Page4Navigator<Product>p1= productService.list(cid,start,size);
        p.setFirstProdutImage(p1.getContent());
        return p1;
    }
    @GetMapping("/products/{id}")
    public Product getOne(@PathVariable("id")int id){
        return productService.getOne(id);
    }
    @PostMapping("/products")
    public Product add(@RequestBody Product product){
        productService.add(product);
        return product;
    }
    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id")int id){
        productService.delete(id);
        return null;
    }
    @PutMapping("/products")
    public Product update(@RequestBody Product product){
        productService.update(product);
        return product;
    }

}

