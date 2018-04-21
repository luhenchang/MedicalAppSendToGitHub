package com.example.ls.shoppingmall.user.bean;

/**
 * Created by 路很长~ on 2017/12/14.
 * 用户注册状态类
 */

public class RegisterResultBean {


    /**
     * RESMSG : 注册成功
     * RESCOD : 000000
     * userID : 20180102142247350684
     */

    private String RESMSG;
    private String RESCOD;
    private String RESOBJ;

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

    public String getRESOBJ() {
        return RESOBJ;
    }

    public void setRESOBJ(String RESOBJ) {
        this.RESOBJ = RESOBJ;
    }

    @Override
    public String toString() {
        return "RegisterResultBean{" +
                "RESMSG='" + RESMSG + '\'' +
                ", RESCOD='" + RESCOD + '\'' +
                ", userID='" + RESOBJ + '\'' +
                '}';
    }
}
