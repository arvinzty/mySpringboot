package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value="start",defaultValue="0")int start,@RequestParam(value="size",defaultValue = "5")int size){
        start=start<0?0:start;
        Page4Navigator<Category> page4Navigator=categoryService.list(start,size,5);
        return page4Navigator;
    }

    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws IOException {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean,image,request);
        return bean;
    }
    public void saveOrUpdateImageFile(Category category,MultipartFile multipartFile,HttpServletRequest  httpRequest) throws IOException {
        String s=httpRequest.getServletContext().getRealPath("/img/category");
        File file=new File(s,category.getId()+".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        multipartFile.transferTo(file);
        BufferedImage image= ImageUtil.change2jpg(file);
        ImageIO.write(image,"jpg",file);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id,HttpServletRequest request){
        String s=request.getServletContext().getRealPath("/img/category");
        File f=new File(s,id+".jpg");
        if (f.exists())
            f.delete();
        categoryService.delete(id);
        return null;
    }

    @GetMapping("/categories/{id}")
    public Category edit(@PathVariable("id")int id){
        return categoryService.getOne(id);
    }

    @PutMapping("/categories/{id}")
    public Object updata(Category category,HttpServletRequest request,MultipartFile image) throws IOException {
        String s=request.getParameter("name");
        category.setName(s);
        if (null!=image)
            saveOrUpdateImageFile(category,image,request);
        categoryService.updata(category);
        return category;
    }
}
