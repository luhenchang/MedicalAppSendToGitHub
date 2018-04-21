package com.example.ls.shoppingmall.user.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 路很长~ on 2017/12/6.
 */

public class PicBean {
    private String id;
    private List<String> urList=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUrList() {
        return urList;
    }

    public void setUrList(List<String> urList) {
        this.urList = urList;
    }

    @Override
    public String toString() {
        return "PicBean{" +
                "id='" + id + '\'' +
                ", urList=" + urList +
                '}';
    }
}
