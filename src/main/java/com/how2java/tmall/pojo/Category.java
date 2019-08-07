package com.how2java.tmall.pojo;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
//实体类
@Entity
//对应表名称
@Table(name="category")
//jpa会生成代理类，这两个增加的属性不转换为json
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Category {
//    主键
    @Id
//    自增长
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//   对应字段，不写的话，自动与表明对应
    @Column(name="id")
    int id;
    @Column(name="name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
