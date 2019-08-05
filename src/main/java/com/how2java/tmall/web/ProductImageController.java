package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.Productimage;

import com.how2java.tmall.service.ProductImageService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    @GetMapping("/products/{pid}/productImages")
    public List<Productimage> list(@PathVariable("pid")int pid,@RequestParam("type")String type){
        Product product=productService.getOne(pid);
        if (ProductImageService.type_single.equals(type)){
            List<Productimage> productimages=productImageService.listSingleProductImages(product);
            return productimages;
        }else if(ProductImageService.type_detail.equals(type)){
            List<Productimage> productimages=productImageService.listDetailProductImages(product);
            return productimages;
        }else {
            return null;
        }
    }

    @PostMapping("/productImages")
    public Productimage add(@RequestParam("pid")int pid, @RequestParam("type")String type, HttpServletRequest request, MultipartFile image) throws IOException {
       String s;
        Product product=productService.getOne(pid);
        Productimage productimage=new Productimage();
        productimage.setProduct(product);
        productimage.setType(type);
        productImageService.add(productimage);
        if (ProductImageService.type_single.equals(type)){
            s="/img/productSingle";
        }else {
            s="/img/productDetail";
        }
        String path=request.getServletContext().getRealPath(s);
        File file=new File(path,productimage.getId()+".jpg");
        file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage bufferedImage= ImageUtil.change2jpg(file);
        ImageIO.write(bufferedImage,".jpg",file);
        if (ProductImageService.type_single.equals(type)){
            String s1=request.getServletContext().getRealPath("/img/productSingle_small");
            String s2=request.getServletContext().getRealPath("/img/productSingle_middle");
            File file1=new File(s1,productimage.getId()+".jpg");
            File file2=new File(s2,productimage.getId()+".jpg");
            file1.getParentFile().mkdirs();
            file2.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, file1);
            ImageUtil.resizeImage(file, 217, 190, file2);
        }
        return productimage;

    }

    @DeleteMapping("/productImages/{id}")
    public String felete(@PathVariable("id")int id,HttpServletRequest request){
        Productimage productimage=productImageService.get(id);
        String s=request.getServletContext().getRealPath("/img");
        if (ProductImageService.type_detail.equals(productimage.getType())){
            s+="/productDetail";
        }else {
            s+="/productSingle";
        }
        File file=new File(s,id+".jpg");
        file.delete();
        if (ProductImageService.type_single.equals(productimage.getType())){
            String s1=request.getServletContext().getRealPath("/img/productSingle_small");
            String s2=request.getServletContext().getRealPath("/img/productSingle_middle");
            File f1=new File(s1,id+".jpg");
            File f2=new File(s2,id+".jpg");
            f1.delete();
            f2.delete();
        }
        productImageService.delete(id);
        return null;
    }



}
