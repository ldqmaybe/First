package com.example.administrator.myapplication.greedDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/11 0011.
 */
@Entity
public class TestUser {
    @Id
    private Long id;
    private String name;
    private int age;
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 237788061)
    public TestUser(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 925009630)
    public TestUser() {
    }
}
