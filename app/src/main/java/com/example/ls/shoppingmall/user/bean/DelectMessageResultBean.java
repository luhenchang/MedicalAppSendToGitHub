package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/4/11.
 */

public class DelectMessageResultBean {

    /**
     * RESMSG : 删除失败
     * RESCOD : 999999
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
