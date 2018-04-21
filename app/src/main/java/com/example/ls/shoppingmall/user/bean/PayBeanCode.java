package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/1/24.
 */

public class PayBeanCode {

    /**
     * RESMSG : null
     * RESCOD : 000000
     */
    private String RESOBJ;
    private String RESMSG;
    private String RESCOD;

    public String getRESOBJ() {
        return RESOBJ;
    }

    public void setRESOBJ(String RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

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
        return "PayBeanCode{" +
                "RESOBJ='" + RESOBJ + '\'' +
                ", RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                '}';
    }
}
