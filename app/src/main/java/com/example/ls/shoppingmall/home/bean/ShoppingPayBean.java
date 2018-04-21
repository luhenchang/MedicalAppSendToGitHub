package com.example.ls.shoppingmall.home.bean;

/**
 * Created by 路很长~ on 2018/3/1.
 */

public class ShoppingPayBean {
    /**
     * subject : 测试用
     * body : 测试用
     * goodsPrice : 0.01
     * userId : 20180320164359015315
     * goodsType : Z
     * goodsOrder : 85
     * goodsNo : 24
     * payType : 2
     */

    private String subject;
    private String body;
    private String goodsPrice;
    private String userId;
    private String goodsType;
    private String goodsOrder;
    private int goodsNo;
    private String payType;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public void setGoodsOrder(String goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public void setGoodsNo(int goodsNo) {
        this.goodsNo = goodsNo;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getUserId() {
        return userId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public String getGoodsOrder() {
        return goodsOrder;
    }

    public int getGoodsNo() {
        return goodsNo;
    }

    public String getPayType() {
        return payType;
    }
}
