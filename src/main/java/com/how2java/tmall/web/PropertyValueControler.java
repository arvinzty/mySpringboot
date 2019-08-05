package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueControler {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;
    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int id){
        Product product=productService.getOne(id);
        propertyValueService.init(product);
        return propertyValueService.list(product);
    }

    @PutMapping("/propertyValues")
    public PropertyValue update(@RequestBody PropertyValue propertyValue){
        propertyValueService.update(propertyValue);
        return propertyValue;
    }
}
