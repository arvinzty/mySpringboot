package com.how2java.tmall.service;

import com.how2java.tmall.dao.OrderDAO;
import com.how2java.tmall.dao.OrderItemDAO;
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    ProductImageService productImageService;

    public List<OrderItem> list(Order order){
        return orderItemDAO.findByOrderOrderByIdDesc(order);
    }
//Order里填充OrderItem数组；并计算商品总金额和数量
    public void fill(Order order){
        float total = 0;
        int totalNumber = 0;
        List<OrderItem>orderItems= list(order);
        for (OrderItem tem:orderItems){
            totalNumber+=tem.getNumber();
            total+=tem.getNumber()*tem.getProduct().getPromotePrice();
            productImageService.setFirstProdutImage(tem.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);

    }

    public void fill(List<Order> orders){
        for (Order tem:orders){
            fill(tem);
        }
    }
}
