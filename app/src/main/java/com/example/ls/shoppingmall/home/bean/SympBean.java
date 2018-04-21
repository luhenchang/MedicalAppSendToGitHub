package com.example.ls.shoppingmall.home.bean;

/**
 * Created by 路很长~ on 2018/1/29.
 */

public class SympBean {
    public boolean flag;
    public SymptomSelectBean.RESOBJBean Sympbean;

    public SympBean(boolean flag, SymptomSelectBean.RESOBJBean Sympbean) {
        this.flag = flag;
        this.Sympbean = Sympbean;
    }

    @Override
    public String toString() {
        return "SympBean{" +
                "flag=" + flag +
                ", Sympbean=" + Sympbean +
                '}';
    }
}
