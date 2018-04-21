package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2018/4/9.
 */

public class MainMessageBean {

    /**
     * RESMSG : 查询成功
     * RESOBJ : 1
     * RESCOD : 000000
     */

    private String RESMSG;
    private int RESOBJ;
    private String RESCOD;

    public void setRESMSG(String RESMSG) {
        this.RESMSG = RESMSG;
    }

    public void setRESOBJ(int RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    public void setRESCOD(String RESCOD) {
        this.RESCOD = RESCOD;
    }

    public String getRESMSG() {
        return RESMSG;
    }

    public int getRESOBJ() {
        return RESOBJ;
    }

    public String getRESCOD() {
        return RESCOD;
    }
}
