package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/4/9.
 */

public class MessageInforBean {

    /**
     * RESMSG : 必输项未赋值
     * RESCOD : 000101
     */

    private String RESMSG;
    private String RESCOD;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public String getRESCOD() {
        return RESCOD;
    }
}
