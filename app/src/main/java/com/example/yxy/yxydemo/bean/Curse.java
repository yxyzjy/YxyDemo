package com.example.yxy.yxydemo.bean;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class Curse {

    String curseName;
    int curseNum;

    public Curse() {
    }

    public Curse(String curseName, int curseNum) {
        this.curseName = curseName;
        this.curseNum = curseNum;
    }

    public String getCurseName() {
        return curseName;
    }

    public void setCurseName(String curseName) {
        this.curseName = curseName;
    }

    public int getCurseNum() {
        return curseNum;
    }

    public void setCurseNum(int curseNum) {
        this.curseNum = curseNum;
    }
}
