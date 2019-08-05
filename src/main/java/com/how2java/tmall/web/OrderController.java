package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.service.OrderItemService;
import com.how2java.tmall.service.OrderService;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @GetMapping("/orders")
    public Page4Navigator<Order> list(@RequestParam(value = "start",defaultValue = "0")int start,@RequestParam(name = "size",defaultValue = "5")int size){
        start=start<0?start:0;
        Page4Navigator<Order> orderPage4Navigator=orderService.list(start,size,5);
        orderItemService.fill(orderPage4Navigator.getContent());
        orderService.removeOrderFromOrderItem(orderPage4Navigator.getContent());
        return orderPage4Navigator;
    }
    @PutMapping("deliveryOrder/{oid}")
    public Object update(@PathVariable("oid")int oid){
        Order o=orderService.get(oid);
        o.setCreateDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return o;
    }
}
