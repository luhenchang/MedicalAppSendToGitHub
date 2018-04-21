package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/3/26.
 */

public class BackMoneyBean {

    /**
     * RESMSG : 参数无效：支付宝订单号和外部订单号不能同时为空
     * RESOBJ :
     * RESCOD : 999999
     */

    private String RESMSG;
    private String RESCOD;

    public String getRESMSG() {
        return RESMSG;
    }

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public String getRESCOD() {
        return RESCOD;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    @Override
    public String toString() {
        return "BackMoneyBean{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                '}';
    }
}

