package com.how2java.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Controller;

import javax.persistence.*;


@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String password;
    private String salt;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnonymousName() {
        if (null!=anonymousName)
            return anonymousName;
        if (null==name)
            anonymousName=null;
        else if (name.length()<=1)
            anonymousName="*";
        else if (name.length()==2)
            anonymousName=name.substring(0,1)+"*";
        else {
            char[] a=name.toCharArray();
            for (int i=1;i<name.length();i++){
                a[i]='*';
            }
            return new String(a);
        }
        return anonymousName;
    }

    public void setAnonymousName(String anonymousName) {
        this.anonymousName = anonymousName;
    }

    @Transient
    private String anonymousName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
