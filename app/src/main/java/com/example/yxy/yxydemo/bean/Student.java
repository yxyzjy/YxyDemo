package com.example.yxy.yxydemo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class Student {

    private String Name;
    private int age;

    public List<Curse> getCurse() {
        return curse;
    }

    public void setCurse(List<Curse> curse) {
        this.curse = curse;
    }

    private List<Curse> curse;


    public Student(String name, int age, List<Curse> curse) {
        Name = name;
        this.age = age;
        this.curse = curse;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
